package com.chen.study.design.pattern.iterator;

import java.util.Iterator;

/**
 * @author 陈添明
 * @date 2019/1/27
 */
public interface Menu {

    Iterator<MenuItem> getIterator();
}
