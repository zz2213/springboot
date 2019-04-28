package com.zz.secondhand.config;

import com.zz.secondhand.Interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Administrator
 * @title: interceptorConfig
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/26 9:06
 */
@Configuration
public class interceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/Token/find").addPathPatterns("/ProductController/findproductype")
        .addPathPatterns("/ProductOrdController/myorder")
        .addPathPatterns("/ProductOrdController/index")
        .addPathPatterns("/SellerOrdController/findsellordbyid")
        .addPathPatterns("/ProductOrdController/updateorder")
        .addPathPatterns("/ProductController/findproductstyle")
        .addPathPatterns("/ProductController/findproductstyle")
        .addPathPatterns("/ProductController/updateProductstatus")
        .excludePathPatterns("/static/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
}
}
