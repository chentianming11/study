package com.chen.study.design.pattern.strategy.promotion;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class PromotionActivityTest {


    /*public static void main(String[] args) {
        PromotionActivity promotionActivity = null;
        String promotionKey = "COUPON";
        if (StringUtils.equals(promotionKey, "COUPON")) {
            promotionActivity = new PromotionActivity(new CouponStrategy());
        } else if (StringUtils.equals(promotionKey, "CASHBACK")) {
            promotionActivity = new PromotionActivity(new CashbackStrategy());
        }
        //......
        // 随着业务的发展，这个判断可能越来越多
        promotionActivity.execute();
    }*/


    public static void main(String[] args) {
        String promotionKey = "COUPON";
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory
                .getPromotionStrategy(promotionKey));
        promotionActivity.execute();
    }
}
