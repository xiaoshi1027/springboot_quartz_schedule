package com.crcb.service.impl;

import com.crcb.entity.ScheduleJob;
import com.crcb.mapper.SchedulerMapper;
import com.crcb.service.SchedulerService;
import com.crcb.task.QuartzJobFactory;
import com.crcb.task.QuartzJobFactoryDisallowConcurrentExecution;
import com.crcb.utils.LogUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Classname SchedulerServiceImpl
 * @Description 定时任务管理
 * @Date 2020/3/18 14:10
 * @Created by gangye
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private SchedulerMapper schedulerMapper;

    /**
     * 从数据库中取 区别于getAllJob
     *
     * @return
     */
    public List<ScheduleJob> getAllTask() {
        return schedulerMapper.getAll();
    }

    /**
     * 添加到数据库中 区别于addJob
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTask(ScheduleJob job) throws SchedulerException {
        job.setCreateTime(new Date());
        schedulerMapper.insert(job);
        addJob(job);
    }

    /**
     * 从数据库中查询job
     */
    public ScheduleJob getTaskById(Long jobId) {
        return schedulerMapper.selectByPrimaryKey(jobId);
    }

    /**
     * 更改任务状态
     *
     * @throws SchedulerException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return;
        }
        if ("stop".equals(cmd)) {
            deleteJob(job);
            job.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else if ("start".equals(cmd)) {
            job.setJobStatus(ScheduleJob.STATUS_RUNNING);
            addJob(job);
        }
        schedulerMapper.update(job);
    }

    /**
     * 更改任务 cron表达式
     *
     * @throws SchedulerException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCron(Long jobId, String cron) throws SchedulerException {
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return;
        }
        job.setCronExpression(cron);
        if (ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            updateJobCron(job);
        }
        schedulerMapper.update(job);

    }

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addJob(ScheduleJob job) throws SchedulerException {
        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        LogUtils.info(scheduler + ".......................................................................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    @PostConstruct
    public void init() throws Exception {

        LogUtils.info("实例化List<ScheduleJob>,从数据库读取....",this);

        // 这里获取任务信息数据
        List<ScheduleJob> jobList = schedulerMapper.getAll();

        for (ScheduleJob job : jobList) {
            addJob(job);
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleJob> getAllJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleJob> getRunningJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
