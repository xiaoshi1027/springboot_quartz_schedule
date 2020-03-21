package com.crcb.service;

import com.crcb.entity.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @Classname SchedulerService
 * @Description 计划任务管理
 * @Date 2020/3/18 14:07
 * @Created by gangye
 */
public interface SchedulerService {

    /**
     * 从数据库中取 区别于getAllJob
     *
     * @return
     */
    List<ScheduleJob> getAllTask();

    /**
     * 添加到数据库中 区别于addJob
     */
    void addTask(ScheduleJob job) throws SchedulerException;

    /**
     * 从数据库中查询job
     */
    ScheduleJob getTaskById(Long jobId);

    /**
     * 更改任务状态
     *
     * @throws SchedulerException
     */
    void changeStatus(Long jobId, String cmd) throws SchedulerException;

    /**
     * 更改任务 cron表达式
     *
     * @throws SchedulerException
     */
    void updateCron(Long jobId, String cron) throws SchedulerException;

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    void addJob(ScheduleJob job) throws SchedulerException;

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    List<ScheduleJob> getAllJob() throws SchedulerException;

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    List<ScheduleJob> getRunningJob() throws SchedulerException;

    /**
     * 暂停一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    void pauseJob(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 恢复一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 删除一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 立即执行job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 更新job时间表达式
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;
}
