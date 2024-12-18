package com.javaacademy.shop_remote.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Aspect
@Slf4j
public class Logging {

    @Pointcut("execution(* com.javaacademy.shop_remote..*(..))")
    public void findAll() {
    }

    @Before("findAll()")
    public void logging(JoinPoint joinPoint) {
        log.debug("Вызов {}, аргументы {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "findAll()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.debug("После вызова результат: {}", result == null ? "void метод" : result.toString());
    }
}
