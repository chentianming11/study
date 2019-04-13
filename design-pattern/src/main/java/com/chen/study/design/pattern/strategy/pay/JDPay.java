package com.chen.study.design.pattern.strategy.pay;


/**
 * 京东白条支付
 * @author mac
 */
public class JDPay extends Payment {
    @Override
    public String getName() {
        return "京东白条支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 300;
    }
}