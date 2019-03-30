package com.chen.study.design.pattern.template;

import java.util.LinkedHashMap;

/**
 * @author 陈添明
 * @date 2019/1/14
 */
public class Coffee {


    public static void main(String[] args) {
        LinkedHashMap<String, String> mapping = new LinkedHashMap<>();
        mapping.put("套餐价格（元/月）", "price");
        mapping.put("房源量", "houseNum");
        mapping.put("推广币", "promotion");
        mapping.put("名称", "name");
        mapping.put("标签", "remark");
        mapping.put("限购因素", "purchaseLimitFactor");
        mapping.put("刷新次数", "flushNum");
        mapping.put("渠道", "supplierName");
        mapping.put("付费类型", "payTypeName");
        mapping.put("版本类型", "version");
        mapping.put("有效期", "validPeriod");
        mapping.put("所属部门ID", "belongOrgId");
        mapping.put("限购方式", "purchaseLimitType");
        mapping.put("开通时间", "createdAt");

        mapping.forEach((key, val) -> {
            System.out.println(key + "---" + val);
        });

        System.out.println("============================");

        mapping.keySet().forEach(s -> {
            System.out.println(s);
        });

        System.out.println("============================");



    }

    void prepareRecipe(){
        // ﻿把水煮沸
        boilWater();
        // ﻿用沸水冲泡咖啡
        brewCoffeeGrinds();
        // ﻿把咖啡倒进杯子
        pourInCup();
        // ﻿加糖加牛奶
        addSugarAndMilk();
    }

    private void addSugarAndMilk() {
        System.out.println("加糖加牛奶");
    }

    private void pourInCup() {
        System.out.println("把咖啡倒进杯子");
    }

    private void brewCoffeeGrinds() {
        System.out.println("用沸水冲泡咖啡");
    }

    private void boilWater() {
        System.out.println("把水煮沸");
    }
}
