package com.crcb.service.impl;

import com.crcb.dto.ProductNumDto;
import com.crcb.mapper.ProductNumMapper;
import com.crcb.service.ProductNumService;
import com.crcb.utils.LogUtils;
import com.crcb.utils.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname ProductNumServiceImpl
 * @Date 2020/3/18 16:53
 * @Created by gangye
 */
@Service
public class ProductNumServiceImpl implements ProductNumService {

    @Autowired
    private ProductNumMapper productNumMapper;

    @Override
    public void productNumForAdd() {
        ProductNumDto productNumDto = new ProductNumDto();
        String batNo = UUID.randomUUID().toString();
        List<Integer> numList = new ArrayList<Integer>();

        for (int i=0; i< RandomNumUtil.getTime(5) ;i++){
            numList.add(RandomNumUtil.getNum(10));
        }
        if (!numList.isEmpty()){
            productNumDto.setBatNo(batNo);
            productNumDto.setNumList(numList);
            productNumDto.setCreateTime(new Date());
            productNumMapper.insertNum(productNumDto);
        }else {
            LogUtils.info("插入随机数失败");
        }
    }

}
