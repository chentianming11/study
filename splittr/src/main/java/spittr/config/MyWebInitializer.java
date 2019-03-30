package spittr.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author 陈添明
 * @date 2019/3/3
 */
public class MyWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)  {
        // 手动注册Servlet
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        // 配置servlet的映射路径
        myServlet.addMapping("/custom/**");
        // 手动注册filter
        FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
        // 配置filter拦截路径
        myFilter.addMappingForUrlPatterns(null, false, "/custom/**");
    }
}
