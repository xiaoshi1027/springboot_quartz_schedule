package com.crcb.run;

import com.crcb.service.ProductNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname AddNumWorker
 * @Description 产生随机数定时任务
 * @Date 2020/3/18 17:37
 * @Created by gangye
 */
@Component
public class AddNumWorker {
    @Autowired
    private ProductNumService productNumService;

    public void work(){
        productNumService.productNumForAdd();
    }

}
