package com.chen.study.design.pattern.strategy.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付方式管理
 *
 * @author 陈添明
 * @date 2019/4/13
 */
public class PaymentManager {

    public static final String ALI_PAY = "AliPay";
    public static final String JD_PAY = "JdPay";
    public static final String WECHAT_PAY = "WechatPay";
    public static final String DEFAULT_PAY = ALI_PAY;

    private static Map<String, Payment> mapping = new HashMap<>();

    static {
        mapping.put(ALI_PAY, new AliPay());
        mapping.put(JD_PAY, new JDPay());
        mapping.put(WECHAT_PAY, new WeChatPay());
    }

    /**
     * 根据payKey获取支付方式
     *
     * @param payKey
     * @return
     */
    public static Payment getPayment(String payKey) {
        Payment payment = mapping.get(payKey);
        return payment == null ? mapping.get(DEFAULT_PAY) : payment;
    }
}
