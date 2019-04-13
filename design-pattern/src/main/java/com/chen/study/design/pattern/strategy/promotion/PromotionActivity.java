package com.chen.study.design.pattern.strategy.promotion;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class PromotionActivity {

    PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    /**
     * 优惠活动开始
     */
    public void execute() {
        promotionStrategy.doPromotion();
    }
}
