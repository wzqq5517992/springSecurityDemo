/**
 * 
 */
package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;

/**第三方过滤器配置及拦截器注册器配置
 * @author zhailiang
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@SuppressWarnings("unused")
	@Autowired
	private TimeInterceptor timeInterceptor;


	/**
	 * 多线程拦截器注册
	 * @param configurer
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.registerDeferredResultInterceptors();
	}

	/**
	 * 单线程拦截器的注册器注册
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}


	/**
	 * 第三方过滤器配置
	 * @return
	 */
	@Bean  //单独配置过滤器需要开启此注解
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
		
	}

}
