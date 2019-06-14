package com.chen.study.custom.util.http.client;

import com.chen.study.custom.util.core.AppException;
import com.chen.study.custom.util.http.handler.GeneticResponseHandler;
import com.chen.study.custom.util.http.handler.WriteToOutputStreamResponseHandler;
import com.chen.study.custom.util.http.helper.HttpClientHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author 陈添明
 * @date 2018/12/25
 */
@Log4j2
@UtilityClass
public class HttpClientUtils {

    private static int connectionRequestTimeout = 30_000;
    private static int connectTimeout = 30_000;
    private static int socketTimeout = 30_000;
    private static int maxConnTotal = 200;
    private static int maxConnPerRoute = 100;
    private static long connTimeToLive = -1;
    private static long maxIdleTime = 10_000;

    private static CloseableHttpClient client;

    static {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                // 响应超时时间
                .setSocketTimeout(socketTimeout)
                // 请求超时时间
                .setConnectTimeout(connectTimeout)
                // 从连接池获取Connection 超时时间
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        client = HttpClients.custom()
                // 设置默认的请求配置
                .setDefaultRequestConfig(defaultRequestConfig)
                // 最大连接数
                .setMaxConnTotal(maxConnTotal)
                // 每个路由的最大连接
                .setMaxConnPerRoute(maxConnPerRoute)
                // 连接存活时间
                .setConnectionTimeToLive(connTimeToLive, TimeUnit.MILLISECONDS)
                // 移除空闲连接
                .evictIdleConnections(maxIdleTime, TimeUnit.MILLISECONDS)
                .build();
    }

    public static <T> T doGet(String url, Map<String, Object> params, Map<String, String> header, Class<T> clz) {
        HttpGet httpGet = HttpClientHelper.buildHttpGet(url, params);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpGet);
        return execute(httpGet, clz);
    }

    public static <T> T doGet(String url, Map<String, Object> params, Class<T> clz) {
        return doGet(url, null, null, clz);
    }

    public static <T> T doGet(String url, Class<T> clz) {
        return doGet(url, null, clz);
    }

    public static <T> T doPost(String url, Map<String, Object> params, Map<String, String> header, Class<T> clz) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPost);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPost);
        return execute(httpPost, clz);

    }

    public static <T> T doPost(String url, Map<String, Object> params, Class<T> clz) {
        return doPost(url, null, null, clz);
    }

    public static <T> T doPost(String url, Class<T> clz) {
        return doPost(url, null, clz);
    }

    public static <T> T doPut(String url, Map<String, Object> params, Map<String, String> header, Class<T> clz) {
        HttpPut httpPut = new HttpPut(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPut);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPut);
        return execute(httpPut, clz);

    }

    public static <T> T doPut(String url, Map<String, Object> params, Class<T> clz) {
        return doPut(url, null, null, clz);
    }

    public static <T> T doPut(String url, Class<T> clz) {
        return doPut(url, null, clz);
    }


    public static <T> T doPostWithFormData(String url, Map<String, Object> params, Map<String, String> header, Class<T> clz) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPost);
        // 设置消息体
        HttpClientHelper.setFormEntity(params, httpPost);
        return execute(httpPost, clz);

    }

    public static <T> T doPostWithFormData(String url, Map<String, Object> params, Class<T> clz) {
        return doPostWithFormData(url, null, null, clz);
    }

    public static <T> T doPostWithFormData(String url, Class<T> clz) {
        return doPostWithFormData(url, null, clz);
    }

    public static <T> T doPutWithFormData(String url, Map<String, Object> params, Map<String, String> header, Class<T> clz) {
        HttpPut httpPut = new HttpPut(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPut);
        // 设置消息体
        HttpClientHelper.setFormEntity(params, httpPut);
        return execute(httpPut, clz);

    }

    public static <T> T doPutWithFormData(String url, Map<String, Object> params, Class<T> clz) {
        return doPutWithFormData(url, null, null, clz);
    }

    public static <T> T doPutWithFormData(String url, Class<T> clz) {
        return doPutWithFormData(url, null, clz);
    }

    public static void doGet(String url, Map<String, Object> params, Map<String, String> header, OutputStream outputStream) {
        HttpGet httpGet = HttpClientHelper.buildHttpGet(url, params);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpGet);
        execute(httpGet, outputStream);
    }

    public static void doGet(String url, Map<String, Object> params, OutputStream outputStream) {
        doGet(url, null, null, outputStream);
    }

    public static void doGet(String url, OutputStream outputStream) {
        doGet(url, null, outputStream);
    }

    public static void doPost(String url, Map<String, Object> params, Map<String, String> header, OutputStream outputStream) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPost);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPost);
        execute(httpPost, outputStream);

    }

    public static void doPost(String url, Map<String, Object> params, OutputStream outputStream) {
        doPost(url, null, null, outputStream);
    }

    public static void doPost(String url, OutputStream outputStream) {
        doPost(url, null, outputStream);
    }

    public static void doPut(String url, Map<String, Object> params, Map<String, String> header, OutputStream outputStream) {
        HttpPut httpPut = new HttpPut(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPut);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPut);
        execute(httpPut, outputStream);

    }

    public static void doPut(String url, Map<String, Object> params, OutputStream outputStream) {
        doPut(url, null, null, outputStream);
    }

    public static void doPut(String url, OutputStream outputStream) {
        doPut(url, null, outputStream);
    }


    public static <T> T doGet(String url, Map<String, Object> params, Map<String, String> header, AbstractResponseHandler<T> handler) {
        HttpGet httpGet = HttpClientHelper.buildHttpGet(url, params);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpGet);
        return execute(httpGet, handler);
    }

    public static <T> T doGet(String url, Map<String, Object> params, AbstractResponseHandler<T> handler) {
        return doGet(url, null, null, handler);
    }

    public static <T> T doGet(String url, AbstractResponseHandler<T> handler) {
        return doGet(url, null, handler);
    }

    public static <T> T doPost(String url, Map<String, Object> params, Map<String, String> header, AbstractResponseHandler<T> handler) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPost);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPost);
        return execute(httpPost, handler);

    }

    public static <T> T doPost(String url, Map<String, Object> params, AbstractResponseHandler<T> handler) {
        return doPost(url, null, null, handler);
    }

    public static <T> T doPost(String url, AbstractResponseHandler<T> handler) {
        return doPost(url, null, handler);
    }

    public static <T> T doPut(String url, Map<String, Object> params, Map<String, String> header, AbstractResponseHandler<T> handler) {
        HttpPut httpPut = new HttpPut(url);
        // 设置请求头
        HttpClientHelper.setHttpHeader(header, httpPut);
        // 设置请求体
        HttpClientHelper.setStringEntity(params, httpPut);
        return execute(httpPut, handler);

    }

    public static <T> T doPut(String url, Map<String, Object> params, AbstractResponseHandler<T> handler) {
        return doPut(url, null, null, handler);
    }

    public static <T> T doPut(String url, AbstractResponseHandler<T> handler) {
        return doPut(url, null, handler);
    }

    private static <T> T execute(HttpUriRequest request, Class<T> clz) {
        try {
            return client.execute(request, new GeneticResponseHandler<>(clz));
        } catch (IOException e) {
            log.info("请求失败：URI={}, method={}", request.getURI(), request.getMethod(), e);
            throw new AppException("请求失败！", e);
        }
    }

    private static <T> T execute(HttpUriRequest request, AbstractResponseHandler<T> handler) {
        try {
            return client.execute(request, handler);
        } catch (IOException e) {
            log.info("请求失败：URI={}, method={}", request.getURI(), request.getMethod(), e);
            throw new AppException("请求失败！", e);
        }
    }

    private static void execute(HttpUriRequest request, OutputStream outputStream) {
        try {
            client.execute(request, new WriteToOutputStreamResponseHandler(outputStream));
        } catch (IOException e) {
            log.info("请求失败：URI={}, method={}", request.getURI(), request.getMethod(), e);
            throw new AppException("请求失败！", e);
        }
    }
}
