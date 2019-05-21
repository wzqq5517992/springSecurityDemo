/**
 * 
 */
package com.imooc.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**拦截器
 * @author zhailiang
 *
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)
	控制器被调用前先执行此方法 拦截器的缺点就是无法获取到控制层方法的入参， 参数需要用切片去获取
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}

	/* (non-Javadoc)
	 控制器成功调用后 会调用此方法
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));

	}

	/* (non-Javadoc)
	不管控制器是否调用成功，控制器结束后都会调用此方法
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
		System.out.println("ex is "+ex);

	}

}
