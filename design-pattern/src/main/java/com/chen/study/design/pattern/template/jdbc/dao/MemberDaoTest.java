package com.chen.study.design.pattern.template.jdbc.dao;

import com.chen.study.design.pattern.template.jdbc.Member;

import java.util.List;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class MemberDaoTest {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<Member> members = memberDao.selectAll();
        System.out.println(members);
    }
}
