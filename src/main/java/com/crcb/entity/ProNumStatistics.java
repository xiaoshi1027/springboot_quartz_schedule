package com.crcb.entity;

import java.util.Date;

/**
 * @Classname ProNumStatistics
 * @Description 统计产生的随机数
 * @Date 2020/3/18 16:01
 * @Created by gangye
 */
public class ProNumStatistics {
    private Integer id;

    private Integer countNum;

    private Integer sumNum;

    private Date statisticsTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public Date getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(Date statisticsTime) {
        this.statisticsTime = statisticsTime;
    }
}
