package com.chen.study.custom.util.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * author:liucheng
 * Date:2018/8/23
 * Time:下午7:07
 */
public class AppException extends RuntimeException {

    @Getter
    @Setter
    private int code = CodeEnum.未知业务失败.code;

    public AppException(String message) {
        super(message);
        simplifyStackTrace();
    }

    public AppException(String message, int code) {
        super(message);
        this.code = code;
        simplifyStackTrace();
    }


    public AppException(String message, Throwable cause) {
        super(message, cause);
        simplifyStackTrace();
    }

    public AppException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
        simplifyStackTrace();
    }

    /**
     * 对于已知报错，去除冗长的trace，清洁日志
     */
    private void simplifyStackTrace() {
        setStackTrace(Arrays.stream(getStackTrace())
                .filter(s -> s.getClassName().startsWith("com.ke") || s.getClassName().startsWith("com.lianjia") || s.getClassName().startsWith("com.dooioo"))
                .toArray(StackTraceElement[]::new));
    }

    /**
     * 错误代码
     * 规范：
     * 0 正常
     * 10000-19999 系统错误（数据库访问失败、第三方资源请求失败等）
     * 20000以上 业务失败（入参错误，没有权限等）
     */
    public enum CodeEnum {
        正常(0),
        未知系统错误(10000),
        未知业务失败(20000),
        未登录(20001),
        无权限(20002),
        数据过期(20003),
        参数错误(20004);
        public int code;

        CodeEnum(int code) {
            this.code = code;
        }
    }
}
