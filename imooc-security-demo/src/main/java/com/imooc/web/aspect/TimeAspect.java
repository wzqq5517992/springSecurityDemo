/**
 * 
 */
package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Aspect
@Component
public class TimeAspect {

	//最前面的*代表任何返回值都可以
	//第二个*代表UserController下的任意方法都可以
	//（）中的..代表方法中的任意参数都行
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");

		//控制层入参通过此方法获取
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is "+arg);
		}
		
		long start = new Date().getTime();

		//控制层调用后返回的结果集
		Object object = pjp.proceed();

		System.out.println("time aspect 耗时:"+ (new Date().getTime() - start));
		
		System.out.println("time aspect end");
		
		return object;
	}

}
