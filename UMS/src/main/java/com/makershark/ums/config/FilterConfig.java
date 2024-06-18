package com.makershark.ums.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.makershark.ums.filter.RateLimitingFilter;
import com.makershark.ums.filter.RequestSanitizingFilter;

@Configuration
public class FilterConfig {
	
	@Bean("rateLimitFilter")
	public FilterRegistrationBean<RateLimitingFilter> rateLimitFilter()	{
		FilterRegistrationBean<RateLimitingFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new RateLimitingFilter());
		filterBean.addUrlPatterns("/api/**");
		filterBean.setOrder(1);
		return filterBean;
	}
	
	@Bean("requestSanitizingFilter")
	public FilterRegistrationBean<RequestSanitizingFilter> requestSanitizingFilter()	{
		FilterRegistrationBean<RequestSanitizingFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new RequestSanitizingFilter());
		filterBean.addUrlPatterns("/api/**");
		filterBean.setOrder(2);
		return filterBean;
	}
	
}
