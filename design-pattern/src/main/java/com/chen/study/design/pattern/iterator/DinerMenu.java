package com.chen.study.design.pattern.iterator;

import java.util.Iterator;

/**
 * 餐厅菜单 - 数组实现
 * @author 陈添明
 * @date 2019/1/27
 */
public class DinerMenu implements Menu {
    private static final int MAX_ITMES = 6;
    private int numberOfItems = 0;

    MenuItem[] menuItems;

    public DinerMenu() {
        this.menuItems = new MenuItem[MAX_ITMES];
        addItem("素食BIT", "素食BIT", true, 2.99);
        addItem("BIT", "培根，生菜和西红柿", false, 2.99);
        addItem("例汤", "例汤", false, 3.29);
        addItem("热狗", "热狗，酸菜莓", false, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);

        if (numberOfItems >= MAX_ITMES){
            System.out.println("餐厅菜单满了，放不下了！");
        }else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    /**
     * 返回迭代器
     * 客户不需要知道餐厅菜单是如何维护菜单项的，也不需要知道迭代器是如何实现的，
     * 客户只需要知道使用这个迭代器遍历菜单项即可。
     * @return
     */
    @Override
    public Iterator getIterator(){
        return new DinerMenuIterator(menuItems);
    }
}
