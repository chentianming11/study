package com.chen.spring.action.c4.aop.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Aspect
@Component
public class TrackCounter {

    private Map<Integer, Integer> trackCounts = new HashMap<>();

    @Pointcut("execution(* com.chen.spring.action.c4.aop.AopDisc.play(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber){}

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber){
        int currentCount = getCount(trackNumber);
        trackCounts.put(trackNumber, currentCount+1);
    }

    private int getCount(int trackNumber) {
        Integer count = trackCounts.get(trackNumber);
        return  count==null ? count : 0 ;
    }
}
