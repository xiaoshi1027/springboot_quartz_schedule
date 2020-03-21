package com.crcb.entity;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname SimpleSchedulerTaskMain
 * @Description
 * @Date 2020/3/18 8:43
 * @Created by gangye
 */
public class SimpleSchedulerTaskMain {
    public static void main(String[] args) {
        //使用quartz创建定时任务
        //1.创建Job对象-->你要做什么
        JobDetail job = JobBuilder.newJob(SimpleScheduleTask.class).build();

        //2.创建Trigger对象-->什么时候做
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5,3)).build();

        try {
            //3.创建Scheduler对象-->在什么时候做什么事
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job,trigger);
            //启动
            scheduler.start();
        } catch (SchedulerException e) {
            System.out.println("定时任务出现失败");
        }

    }
}
