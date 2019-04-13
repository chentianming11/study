package com.chen.study.design.pattern.strategy.promotion;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class CouponStrategy implements PromotionStrategy {
    /**
     * 执行优惠方法
     */
    @Override
    public void doPromotion() {
        System.out.println("使用优惠券抵扣！！！");
    }
}
