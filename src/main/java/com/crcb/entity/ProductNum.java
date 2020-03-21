package com.crcb.entity;

import java.util.Date;

/**
 * @Classname ProductNum
 * @Description 生产数据实体类
 * @Date 2020/3/18 15:53
 * @Created by gangye
 */
public class ProductNum {
    private Integer id;

    //插入数据的批次号
    private String batNo;

    //插入的随机数
    private Integer proNum;

    //插入数据的时间
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatNo() {
        return batNo;
    }

    public void setBatNo(String batNo) {
        this.batNo = batNo;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
