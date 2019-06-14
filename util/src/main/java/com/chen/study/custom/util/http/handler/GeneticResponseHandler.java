package com.chen.study.custom.util.http.handler;

import com.alibaba.fastjson.JSONObject;
import com.chen.study.custom.util.http.helper.HttpClientHelper;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 陈添明
 * @date 2018/12/25
 */
public class GeneticResponseHandler<T> extends AbstractResponseHandler<T> {

    private Class<T> clz;

    public GeneticResponseHandler(Class<T> clz){
        this.clz = clz;
    }

    @Override
    public T handleEntity(HttpEntity entity) throws IOException {
        if (clz == String.class){
            // String
            return (T) EntityUtils.toString(entity, HttpClientHelper.UTF_8);
        } else if (clz == byte[].class){
            // byte[]
            return (T) EntityUtils.toByteArray(entity);
        } else {
            // other
            String result = EntityUtils.toString(entity, HttpClientHelper.UTF_8);
            return JSONObject.parseObject(result, clz);
        }
    }
}
