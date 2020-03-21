package com.crcb.utils;

import java.util.Random;

/**
 * @Classname RandomNumUtil
 * @Description 产生范围内的随机数
 * @Date 2020/3/18 16:57
 * @Created by gangye
 */
public class RandomNumUtil {
    //产生num以内的随机数包含0
    public static Integer getNum(Integer num){
        Random random = new Random();
        return random.nextInt(num);
    }

    //产生（0,num]的随机数
    public static Integer getTime(Integer num){
        Random random = new Random();
        Integer time = random.nextInt(num);
        while (time.intValue()==0){
            time = getTime(num);
        }
        return time;
    }
}
