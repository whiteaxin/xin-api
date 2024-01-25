package com.zhx.project.config;



import com.zhx.project.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    // 对swagger的请求不进行拦截
    String[] excludePatterns = new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
            "/api", "/api-docs", "/api-docs/**", "/doc.html/**","/api/doc.html/**"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加令牌拦截器，并设置其拦截路径为/**
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/user/login")
                .excludePathPatterns(excludePatterns)
                .excludePathPatterns("/user/register")
                .addPathPatterns("/**");
    }

}
