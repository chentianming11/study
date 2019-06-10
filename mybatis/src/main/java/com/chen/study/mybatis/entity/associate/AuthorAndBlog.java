package com.chen.study.mybatis.entity.associate;

import lombok.Data;

import java.util.List;


@Data
public class AuthorAndBlog {

    /**
     * 作者ID
     */
    Integer authorId;

    /**
     * 作者名称
     */
    String authorName;

    /**
     * 文章和评论列表
     */
    List<BlogAndComment> blog;
}
