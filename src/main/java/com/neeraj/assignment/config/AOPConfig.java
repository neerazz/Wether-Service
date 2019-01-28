package com.neeraj.assignment.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfig {

	private final Logger log = LoggerFactory.getLogger(AOPConfig.class);

	@Pointcut("execution(* com.neeraj.assignment.controller.*.*(..))")
	private void controllerPointCutDeclatation() {
	}

	@Pointcut("execution(* com.neeraj.assignment.service.*.*(..))")
	private void servicePointCutDeclatation() {
	}

	@Pointcut("execution(* com.neeraj.assignment.model.*.*(..))")
	private void modelPointCutDeclatation() {
	}

	@Pointcut("execution(* com.neeraj.assignment.security.*.*(..))")
	private void securityPointCutDeclatation() {
	}

	@Pointcut("execution(* com.neeraj.assignment.*.*(..))")
	private void allPointCutDeclatation() {
	}

	@Around("servicePointCutDeclatation() || modelPointCutDeclatation()")
//	@Around("servicePointCutDeclatation()")
	private Object aroundAspect(ProceedingJoinPoint joinPoint) {

		final String method = joinPoint.getStaticPart().getSignature().getDeclaringType().getName() + "."
				+ joinPoint.getStaticPart().getSignature().getName() + "()";

		long begin = System.currentTimeMillis();
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Throwable e) {
			log.error("There was an error in " + method + ". Error Message :" + e.getMessage());
			printArgs(joinPoint.getArgs());
			e.printStackTrace();
		} finally {
			long end = System.currentTimeMillis();
			long difference = end - begin;
			log.trace(method + " ran for >>>>>>> " + difference / 1000 + " seconds. <<<<<<<");
		}
		return object;
	}

	private void printArgs(Object[] args) {
		for (Object arg : args) {
			log.trace("Arguments used:\t\t" + arg);
		}
	}
}
