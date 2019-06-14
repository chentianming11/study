package com.chen.study.custom.util.http.helper;

import com.alibaba.fastjson.JSON;
import com.chen.study.custom.util.core.AppException;
import com.chen.study.custom.util.core.Is;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陈添明
 * @date 2018/12/26
 */
@UtilityClass
@Log4j2
public class HttpClientHelper {
    public static final String UTF_8 = "utf-8";
    public static final String APPLICATION_JSON_VALUE = "application/json";

    /**
     * 将Map解析为NameValuePairs
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> parseNameValuePairs(Map<String, Object> params) {
        // 处理请求参数
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        params.entrySet().forEach(entry -> {
            BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
            nameValuePairList.add(nameValuePair);
        });
        return nameValuePairList;
    }

    public static void setHttpHeader(Map<String, String> header, HttpRequestBase http) {
        if (!Is.empty(header)) {
            header.entrySet().forEach(entry -> http.setHeader(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 构建HttpGet
     */
    public static HttpGet buildHttpGet(String url, Map<String, Object> params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            // 设置参数
            if (!Is.empty(params)) {
                List<NameValuePair> nameValuePairList = parseNameValuePairs(params);
                uriBuilder.setParameters(nameValuePairList);
            }
            // 获取httpGet对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            return httpGet;
        } catch (URISyntaxException e) {
            log.info("URI语法错误", e);
            throw new AppException("URI语法错误", e);
        }
    }

    public static void setStringEntity(Map<String, Object> params, HttpEntityEnclosingRequestBase request) {
        String jsonString = JSON.toJSONString(params);
        StringEntity entity = new StringEntity(jsonString, UTF_8);
        entity.setContentType(APPLICATION_JSON_VALUE);
        entity.setContentEncoding(UTF_8);
        request.setEntity(entity);
    }

    public static void setFormEntity(Map<String, Object> params, HttpEntityEnclosingRequestBase request) {
        List<NameValuePair> nameValuePairList = parseNameValuePairs(params);
        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairList, UTF_8));
        } catch (UnsupportedEncodingException e) {
            log.info("不支持的编码异常", e);
            throw new AppException("不支持的编码异常");
        }
    }
}
