package com.chen.study.design.pattern.iterator;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 陈添明
 * @date 2019/1/27
 */
@Data
public class Waitress {

    private List<Menu> menus = new ArrayList<>();

    public void printMenu(){
        for (Menu menu : menus) {
            Iterator<MenuItem> iterator = menu.getIterator();
            printMenu(iterator);
        }
    }

    private void printMenu(Iterator<MenuItem> iterator){
        while (iterator.hasNext()){
            MenuItem item = iterator.next();
            System.out.println(item);
        }
        System.out.println("----------");

    }

}
