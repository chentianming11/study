package com.chen.study.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Comment {
    Integer commentId; // 评论ID
    Integer bid; // 所属文章ID
    String content; // 内容

}
