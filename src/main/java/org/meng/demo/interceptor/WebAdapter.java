/*
package org.meng.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAdapter extends WebMvcConfigurerAdapter{

	 	@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        //众多的拦截器组成了一个拦截器链
	        */
/**
	         * 说明：
	         * addPathPatterns 用于添加拦截规则
	         * excludePathPatterns 用户排除拦截
	         *//*

	        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//	        registry.addInterceptor(new CustomInterceptor2()).addPathPatterns("/**");
	        super.addInterceptors(registry);
	    }
}
*/
