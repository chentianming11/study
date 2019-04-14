package com.chen.study.design.pattern.adapter.ac2dc;

/**
 * 目标接口
 * 新需求要求支持DC5V的接口
 * 但是DC5V可以通过AC220V转换得到
 * @author 陈添明
 * @date 2019/4/14
 */
public interface IDC5 {

    int outputDC5V();
}
