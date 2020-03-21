package com.crcb.service.impl;

import com.crcb.dto.ProNumStatisticsDto;
import com.crcb.entity.ProNumStatistics;
import com.crcb.mapper.ProNumStatisticsMapper;
import com.crcb.mapper.ProductNumMapper;
import com.crcb.service.ProNumStatisticsService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Classname ProNumStatisticsImpl
 * @Date 2020/3/18 17:16
 * @Created by gangye
 */
@Service
public class ProNumStatisticsImpl implements ProNumStatisticsService {
    @Autowired
    private ProductNumMapper productNumMapper;

    @Autowired
    private ProNumStatisticsMapper proNumStatisticsMapper;
    @Override
    public void addStatisticsResult() {
        ProNumStatisticsDto proNumStatisticsDto = productNumMapper.countNum();
        ProNumStatistics proNumStatistics = new ProNumStatistics();
        proNumStatistics.setCountNum(proNumStatisticsDto.getCountNum());
        proNumStatistics.setSumNum(proNumStatisticsDto.getSumNum());
        proNumStatistics.setStatisticsTime(new Date());
        proNumStatisticsMapper.addStatisticsResult(proNumStatistics);
    }
}
