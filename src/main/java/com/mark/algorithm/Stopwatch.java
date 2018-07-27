package com.mark.algorithm;

/**
 * 计算花费时间
 * @author mark
 * @date 2018/7/26.
 */
public class Stopwatch {
    private long startTime = 0;

    public Stopwatch(){
        startTime = System.currentTimeMillis();
    }

    public double elapsedTime(){
        return System.currentTimeMillis() - startTime;
    }
}
