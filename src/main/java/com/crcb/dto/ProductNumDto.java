package com.crcb.dto;

import java.util.Date;
import java.util.List;

/**
 * @Classname ProductNumDto
 * @Description 插入数据扩展类
 * @Date 2020/3/18 16:07
 * @Created by gangye
 */
public class ProductNumDto {
    //插入数据的批次号
    private String batNo;

    //插入数据的集合
    private List<Integer> numList;

    //插入的时间
    private Date createTime;

    public String getBatNo() {
        return batNo;
    }

    public void setBatNo(String batNo) {
        this.batNo = batNo;
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public void setNumList(List<Integer> numList) {
        this.numList = numList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
