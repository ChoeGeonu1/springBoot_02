package com.example.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	  @Override
	    public boolean preHandle(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object handler
	    ) throws Exception {
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
		  System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
	        logger.info("[MYTEST] preHandle");
	        return true;
	    }

	    @Override
	    public void postHandle(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object handler,
	            ModelAndView modelAndView
	    ) throws Exception {
	        logger.info("[MYTEST] postHandle");
	    }

	    @Override
	    public void afterCompletion(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object object,
	            Exception ex
	    ) throws Exception {
	        logger.info("[MYTEST] afterCompletion");
	    }
}
