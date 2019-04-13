package com.chen.study.design.pattern.strategy.pay;

/**
 * 定义支付规范和支付逻辑
 */
public abstract class Payment {

    /**
     * 支付类型名称
     * @return
     */
    public abstract String getName();

    /**
     * 查询余额
     * @param uid
     * @return
     */
    protected abstract double queryBalance(String uid);

    /**
     * 扣款支付
     * @param uid
     * @param amount
     * @return
     */
    public PayState pay(String uid, double amount) {
        if (queryBalance(uid) < amount) {
            return new PayState(500, "支付失败", "余额不足");
        }
        return new PayState(200, "支付成功", "支付金额:" + amount);
    }
}