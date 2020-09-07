package com.cook.talk.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Aspect
public class LogAspectJ {
	
	public LogAspectJ() {
		System.out.println("::" + getClass() + " default Construct");
	}
	
	
	@Around("execution(* com.cook.talk.model.dao..*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("-------------------------------------------------------");
		System.out.println("[Log] Call Method :"+joinPoint.getSignature().toShortString());
		if(joinPoint.getArgs().length !=0){
			System.out.println("[Log] parameter: "+ joinPoint.getArgs()[0]);
		}
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		}catch (Exception e) {
			System.out.println("[Log] error : "+ joinPoint.getTarget()+"에서 오류남 !");
			e.printStackTrace();
		}

		System.out.println("[Log] Bean Mapping  : "+obj);
		System.out.println("-------------------------------------------------------");

		return obj;
	}
}