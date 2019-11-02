package com.chen.study.format.starter.format;

import com.alibaba.fastjson.JSON;

public class JsonFormatProcessor implements FormatProcessor{
    @Override
    public <T> String format(T obj) {
        return "JsonFormatProcessor:"+ JSON.toJSONString(obj);
    }
}
