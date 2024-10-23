package com.estsoft.springproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLoggingAop {
    // 특정 method() 호출했을 때, method의 수행 시간 측정

    @Around("execution(* com.estsoft.springproject.book..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint 앞 뒤에서 실행?됨

        long startTimeMs = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finishTImeMs = System.currentTimeMillis();
            long timeMs = finishTImeMs - startTimeMs;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
