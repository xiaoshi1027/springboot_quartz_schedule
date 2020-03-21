package com.crcb.entity;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Classname SimpleScheduleTask
 * @Description 简单的定时任务
 * @Date 2020/3/17 16:50
 * @Created by gangye
 */
public class SimpleScheduleTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("当前执行"+Thread.currentThread().getName()+"--"+new Date());
    }
}
