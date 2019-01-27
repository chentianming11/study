package com.chen.study.design.pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 菜单项
 * @author 陈添明
 * @date 2019/1/27
 */
@Data
@AllArgsConstructor
public class MenuItem {
    private String name;
    private String description;
    /**
     * 是否为素食
     */
    private boolean vegetarian;
    private double price;
}
