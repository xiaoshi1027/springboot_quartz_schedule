package com.crcb.mapper;

import com.crcb.dto.ProNumStatisticsDto;
import com.crcb.dto.ProductNumDto;

/**
 * @Classname ProductNumMapper
 * @Date 2020/3/18 16:05
 * @Created by gangye
 */
public interface ProductNumMapper {
    //插入数据
    void insertNum(ProductNumDto productNumDto);

    //统计数据
    ProNumStatisticsDto countNum();
}
