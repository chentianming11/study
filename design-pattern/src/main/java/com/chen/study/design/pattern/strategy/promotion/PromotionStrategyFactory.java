package com.chen.study.design.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * 优惠策略 工厂
 * @author 陈添明
 * @date 2019/4/13
 */
public class PromotionStrategyFactory {

   /**
    * 注册是单例
    */
   private static Map<String, PromotionStrategy> mapping = new HashMap<>();

   static {
      mapping.put(PromotionKey.COUPON, new CouponStrategy());
      mapping.put(PromotionKey.CASHBACK, new CashbackStrategy());
      mapping.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
   }

   /**
    * 根据优惠标识获取对应的优惠策略对象
    * @param promotionKey
    * @return
    */
   public static PromotionStrategy getPromotionStrategy(String promotionKey) {
      return mapping.get(promotionKey);
   }


   private interface PromotionKey {

      String COUPON = "COUPON";

      String CASHBACK = "CASHBACK";

      String GROUPBUY = "GROUPBUY";

   }
}
