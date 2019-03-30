package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spittr.web.WebConfig;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * ﻿指定ContextLoaderListener应用上下文的配置类。
   * @return
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { RootConfig.class };
  }

  /**
   * ﻿指定DispatcherServlet应用上下文的配置类
   * @return
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebConfig.class };
  }

  /**
   * 将DispatcherServlet映射到 /
   * 即DispatcherServlet处理应用的所有请求
   * @return
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }


  /**
   * 自定义DispatcherServlet配置
   * @param registration
   */
  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(
            new MultipartConfigElement("/tmp/spittr/uploads", 2097152, 4194304, 0));
  }

  /**
   * 注册Filter
   * @return
   */
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[]{new MyFilter()};
  }
}