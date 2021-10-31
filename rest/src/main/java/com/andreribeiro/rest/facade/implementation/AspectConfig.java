package com.andreribeiro.rest.facade.implementation;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Aspect
@Component
@Configuration
public class AspectConfig extends OncePerRequestFilter{
	
    private static final Logger log = LoggerFactory.getLogger(AspectConfig.class);	
    public static final String REF_ID = "refId";
	
	@Before("execution(* com.andreribeiro.rest.controller..*.*(..))")
	public void mdcPut(JoinPoint joinPoint) {
		//to get method name
		log.info("added mdc key before calling "+joinPoint.getSignature().getName()+"()");
		
		//to get method args
		Object[] methodArgs = joinPoint.getArgs();
		for (Object oneArg: methodArgs) {
			System.out.println("arg="+oneArg);
        }
		MDC.put(REF_ID, UUID.randomUUID().toString().replace("-", "").substring(0, 12));
	}	
	
	@After("execution(* com.andreribeiro.rest.controller..*.*(..))")
	public void mdcRemove(JoinPoint joinPoint) {
		log.info("removed mdc key after calling "+joinPoint.getSignature().getName()+"()");
		MDC.remove(REF_ID);
    }	
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
           throws ServletException, IOException {
       response.setHeader("id", MDC.get(REF_ID)); // HTTP 1.1.
       log.info("filter log.");
       filterChain.doFilter(request, response);
    }
}
