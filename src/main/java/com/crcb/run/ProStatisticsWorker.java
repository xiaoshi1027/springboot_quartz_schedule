package com.crcb.run;

import com.crcb.service.ProNumStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname ProStatisticsWorker
 * @Description 统计数据定时任务,并将统计结果添加到数据库中
 * @Date 2020/3/18 17:43
 * @Created by gangye
 */
@Component
public class ProStatisticsWorker {
    @Autowired
    private ProNumStatisticsService proNumStatisticsService;

    public void work(){
        proNumStatisticsService.addStatisticsResult();
    }
}
