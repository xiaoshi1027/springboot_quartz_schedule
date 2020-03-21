package com.crcb.mapper;

import com.crcb.entity.ScheduleJob;
import com.crcb.utils.ParamMap;

import java.util.List;

/**
 * @Classname SchedulerDao
 * @Date 2020/3/18 13:41
 */
public interface SchedulerMapper {
    int delete(Long jobId);

    int insert(ScheduleJob record);

    ScheduleJob selectByClassAndMethod(ParamMap paramMap);

    ScheduleJob selectByPrimaryKey(Long jobId);

    int update(ScheduleJob record);

    List<ScheduleJob> getAll();
}
