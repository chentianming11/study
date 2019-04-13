package com.chen.study.design.pattern.strategy.pay;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class Order {

    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    /**
     * 完美地解决了 switch 的过程，不需要在代码逻辑中写 switch 了
     * 也不用写 if else
     * @return
     */
    public PayState pay() {
        return pay(PaymentManager.DEFAULT_PAY);
    }

    public PayState pay(String payKey) {
        Payment payment = PaymentManager.getPayment(payKey);
        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为:" + amount + "，开始扣款...");
        return payment.pay(uid, amount);
    }
}
