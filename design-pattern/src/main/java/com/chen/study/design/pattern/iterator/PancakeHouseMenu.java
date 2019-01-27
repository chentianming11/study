package com.chen.study.design.pattern.iterator;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 煎饼屋菜单-ArrayList实现
 * @author 陈添明
 * @date 2019/1/27
 */
@Data
public class PancakeHouseMenu implements Menu {
    private ArrayList<MenuItem> menuItems;
    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItem("K&B薄煎饼早餐", "薄煎饼，鸡蛋和吐司", true, 2.99);
        addItem("薄煎饼早餐", "薄煎饼带鸡蛋、香肠", false, 2.99);
        addItem("蓝莓薄煎饼", "蓝莓薄煎饼", true, 3.49);
        addItem("松饼", "松饼，可以选择蓝莓或者草莓", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    /**
     * 返回迭代器
     * 客户不需要知道餐厅菜单是如何维护菜单项的，也不需要知道迭代器是如何实现的，
     * 客户只需要知道使用这个迭代器遍历菜单项即可。
     * @return
     */
    @Override
    public Iterator getIterator(){
        return menuItems.iterator();
    }
}
