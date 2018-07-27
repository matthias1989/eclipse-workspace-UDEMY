package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	// setup logger
	Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	// add @Before advice
	@Before("forAppFlow()")
	private void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before - calling method: "+ theMethod);
		
		// display arguments to the method
		Object[] args = theJoinPoint.getArgs();
		for(Object tempArg : args) {
			myLogger.info("====> argument: " +tempArg.toString());
		}
	}
	
	// add @afterReturning advice
	@AfterReturning(pointcut="forAppFlow()",returning="theResult")
	private void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		// display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning from method: "+ theMethod);
		
		// display data to return
		myLogger.info("====> result: "+theResult);
	}
}
