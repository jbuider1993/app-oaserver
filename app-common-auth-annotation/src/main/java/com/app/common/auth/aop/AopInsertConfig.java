package com.app.common.auth.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注入的配置
 * @author 卫志强
 *
 */
@Configuration
public class AopInsertConfig implements WebMvcConfigurer {

	@Bean
	public AuthHandler authAop() {
		return new AuthHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注入权限拦截器
		registry.addInterceptor(authAop()).addPathPatterns("/**");
	}

}
