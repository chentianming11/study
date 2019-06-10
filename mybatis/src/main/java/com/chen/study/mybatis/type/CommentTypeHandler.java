package com.chen.study.mybatis.type;

import com.alibaba.fastjson.JSON;
import com.chen.study.mybatis.entity.Comment;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: qingshan
 * @Date: 2019/3/7 22:28
 *
 */
public class CommentTypeHandler extends BaseTypeHandler<Comment> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Comment parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Comment getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 根据列名获取 String 类型的参数的时候调用，JDBC类型到java类型
        // 注意只有在字段上添加typeHandler属性才会生效
        String string = rs.getString(columnName);
        return JSON.parseObject(string, Comment.class);
    }

    @Override
    public Comment getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据下标获取 String 类型的参数的时候调用
        String string = rs.getString(columnIndex);
        return JSON.parseObject(string, Comment.class);
    }

    @Override
    public Comment getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return JSON.parseObject(string, Comment.class);
    }
}