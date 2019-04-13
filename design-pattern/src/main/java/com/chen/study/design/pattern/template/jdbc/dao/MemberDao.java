package com.chen.study.design.pattern.template.jdbc.dao;

import com.chen.study.design.pattern.template.jdbc.JdbcTemplate;
import com.chen.study.design.pattern.template.jdbc.Member;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class MemberDao extends JdbcTemplate {

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Member> selectAll() {

        String sql = "select * from t_member";
        List<Member> members = executeQuery(sql, (rs, rowNum) -> {
            Member member = new Member()
                    .setUsername(rs.getString("username"))
                    .setAge(rs.getInt("age"))
                    .setNickName(rs.getString("nick_name"))
                    .setPassword(rs.getString("password"));
            return member;
        }, null);

        return members;
    }
}
