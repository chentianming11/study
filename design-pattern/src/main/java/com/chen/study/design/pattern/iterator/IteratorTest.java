package com.chen.study.design.pattern.iterator;

/**
 * @author 陈添明
 * @date 2019/1/27
 */
public class IteratorTest {

    public static void main(String[] args) {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();
        Waitress waitress = new Waitress();
        waitress.getMenus().add(dinerMenu);
        waitress.getMenus().add(pancakeHouseMenu);
        waitress.printMenu();
    }
}
