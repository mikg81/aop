package com.aop.performance;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class LogKafkaMessageTraceAspect {

    public static final String UUID = "uuid";

    @Around("@annotation(LogKafkaMessageTrace)")
    public Object logKafkaMessageTrace(ProceedingJoinPoint joinPoint) throws Throwable {

        String uuid = MDC.get(UUID);
        if(StringUtils.isEmpty(uuid)) {
            uuid = java.util.UUID.randomUUID().toString();
        }
        RequestUuid.setUuid(uuid);

        MDC.put(UUID, "[" + RequestUuid.getUuid() + "]");

        return joinPoint.proceed();
    }

    @After("@annotation(LogKafkaMessageTrace)")
    public void afterLogKafkaMessageTrace(JoinPoint joinPoint) {
        MDC.remove(UUID);
    }
}