package com.chen.study.web.service;

import com.chen.study.web.entity.Config;
import com.chen.study.web.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigService {

    @Autowired
    ConfigMapper configMapper;

    public static Long configId = 1L;

    public void insert() {
        for (int i = 1; i <= 10; i++) {
            Config config = new Config();
            config.setConfigId(i);
            config.setParaName("name" + i);
            config.setParaValue("value" + i);
            config.setParaDesc("desc" + i);
            configId++;
            configMapper.insert(config);
        }
    }

    public void update(Integer configId) {
        Config config = configMapper.selectByPrimaryKey(configId);
        config.setParaDesc("after modified. 0711");
        configMapper.updateByPrimaryKey(config);
    }

    public Config geConfigById(Integer id) {
        return configMapper.selectByPrimaryKey(id);
    }

}
