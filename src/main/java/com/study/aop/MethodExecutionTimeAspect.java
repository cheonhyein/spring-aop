package com.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Aspect : spring AOP에서 관점 클래스임을 나타내는 어노테이션
@Aspect
@Component
public class MethodExecutionTimeAspect {
    /**
     *
     * @Around: Advice(조언)를 정의하는 어노테이션, 메서드 호출 전후에 실행될 코드를 정의
     * 'execution(* com.study.aop..*(..))' : 포인트컷 표현식
     *
     */
    @Around("execution(* com.study.aop..*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // advice start
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long executionEnd = System.nanoTime() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionEnd + " ns");
        return proceed;
        // advice end
    }
}
