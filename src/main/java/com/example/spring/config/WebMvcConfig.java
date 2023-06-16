package com.example.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.spring.interceptor.MyInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
	System.out.println("#####################################");
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/*/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/*/**") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/*/**/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/*/**/**") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/**/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/**/**") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/**/**/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .addPathPatterns("/**/**/**") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .excludePathPatterns("/boards"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
}
