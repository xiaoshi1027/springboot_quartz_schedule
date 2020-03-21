package com.crcb.entity;

import java.util.Date;

/**
 * @Classname ScheduleJob
 * @Description 任务
 * @Date 2020/3/18 18:47
 * @Created by gangye
 */
public class ScheduleJob {
    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";

    private Long jobId;

    private Date createTime;

    private Date updateTime;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 描述
     */
    private String description;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;
    /**
     * 任务是否有状态
     */
    private String isConcurrent;
    /**
     * spring bean
     */
    private String springId;
    /**
     * 任务调用的方法名
     */
    private String methodName;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass == null ? null : beanClass.trim();
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent == null ? null : isConcurrent.trim();
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId == null ? null : springId.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }
}
