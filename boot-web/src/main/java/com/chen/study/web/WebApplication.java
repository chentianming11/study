package com.chen.study.web;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈添明
 * @date 2020/3/15
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chen.study.web.mapper")
public class WebApplication {

    public static void main(String[] args) {
        initFlowRules();
        SpringApplication.run(WebApplication.class, args);
    }

    //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>(); //限流规则的集合
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello");//资源(方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
