package com.chen.study.design.pattern.delegate.mvc;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * DispatcherServlet：
 * 所有的请求统一委派给DispatcherServlet进行处理
 * @author 陈添明
 * @date 2019/4/13
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 处理器映射
     */
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    @SneakyThrows
    public void init() {
        // 初始化处理器映射
        handlerMapping.add(new Handler()
                .setController(new MemberController())
                .setMethod(MemberController.class.getMethod("getMemberById", String.class))
                .setUrl("/api/member/getMemberById"));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        doDispatch(req, resp);
    }


    /**
     * 请求分发，根据不同的url，执行不同的逻辑
     * @param req
     * @param resp
     */
    @SneakyThrows
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

        //1、获取用户请求的 url
        // 如果按照 J2EE 的标准、每个 url 对对应一个 Serlvet，url 由浏览器输入
        String uri = req.getRequestURI();
        //2、Servlet 拿到 url 以后，要做权衡(要做判断，要做选择)
        // 根据用户请求的 URL，去找到这个 url 对应的某一个 java 类的方法
        //3、通过拿到的 URL 去 handlerMapping(我们把它认为是策略常量)
        Handler handle = null;
        for (Handler h : handlerMapping) {
            if (uri.equals(h.getUrl())) {
                handle = h;
                break;
            }
        }
        if (handle == null) {
            // 没有找到处理器，回404
            resp.getWriter().write("404 not Found");
            return;
        }
        // 4、将具体的任务分发给 Method(通过反射去调用其对应的方法)
        Object result = handle.getMethod().invoke(handle.getController(), req.getParameter("mid"));
        resp.getWriter().write(result.toString());
    }

    @Data
    @Accessors(chain = true)
    class Handler {
        private Object controller;
        private Method method;
        private String url;
    }
}
