package com.chen.study.web.service;

import com.chen.study.web.entity.UserInfo;
import com.chen.study.web.mapper.UserInfoMapper;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public static Long userId = 1L;

    public void insert() {
        for (int i = 1; i <= 100; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount("account" + i);
            userInfo.setPassword("password" + i);
            userInfo.setUserName("name" + i);
            userId++;
            userInfoMapper.insert(userInfo);
        }
    }

    public UserInfo getUserInfoByUserId(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public List<UserInfo> selectByRange(Long firstId, Long lastId) {
        return userInfoMapper.selectByRange(firstId, lastId);
    }

    /**
     * 测试跨库事务
     */
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {
        // 多库操作
    }
}
