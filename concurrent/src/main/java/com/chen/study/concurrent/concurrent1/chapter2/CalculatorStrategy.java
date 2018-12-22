package com.chen.study.concurrent.concurrent1.chapter2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/

@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary, double bonus);
}
