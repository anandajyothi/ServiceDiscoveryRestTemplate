package com.setplex.ott.subscription.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AspectLogging {

	Logger logger = null;

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		ObjectMapper mapper = new ObjectMapper();
		logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info("Start - " + joinPoint.getTarget().getClass().getSimpleName() + "."
				+ joinPoint.getSignature().getName());
		Object[] array = joinPoint.getArgs();
		logger.info("Arguments: " + mapper.writeValueAsString(array));
		Object object = joinPoint.proceed();
		watch.stop();
		logger.info("End - " + joinPoint.getTarget().getClass().getSimpleName() + "."
				+ joinPoint.getSignature().getName() + " elapsed time : " + watch.getTotalTimeMillis());
		logger.info("Response: " + mapper.writeValueAsString(object));
		return object;
	}
}