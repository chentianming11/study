package com.chen.study.web;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈添明
 */
public class SentinelDemo {

    private static String resource = "doTest";

    public static void main(String[] args) {
        // 初始化限流规则
        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry(resource);

                // 执行业务逻辑
                System.out.println("Hello Word");

            } catch (BlockException e) {
                //如果被限流了，那么会抛出这个异常
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    // 释放
                    entry.exit();
                }
            }
        }

    }

    private static void initFlowRules() {
        // 限流规则集合
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        //资源(方法名称、接口）
        flowRule.setResource(resource);
        //限流的阈值的类型
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);

    }
}
