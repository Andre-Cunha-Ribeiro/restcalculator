package com.andreribeiro.rest.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configuration
public class AspectConfig{
	
    public static final String REF_ID = "refId";
	
	@After("execution(* com.andreribeiro.rest.controller..*.*(..))")
	public void mdcRemove(JoinPoint joinPoint) {
		MDC.remove(REF_ID);
	}	
	
}
