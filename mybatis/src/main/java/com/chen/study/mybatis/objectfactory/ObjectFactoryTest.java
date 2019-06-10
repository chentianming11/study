package com.chen.study.mybatis.objectfactory;


import com.chen.study.mybatis.entity.Blog;

/**
 * @Author: qingshan
 * @Date: 2019/3/25 19:41
 */
public class ObjectFactoryTest {
    public static void main(String[] args) {
        CustomObjectFactory factory = new CustomObjectFactory();
        Blog myBlog = (Blog) factory.create(Blog.class);
        System.out.println(myBlog);
    }
}
