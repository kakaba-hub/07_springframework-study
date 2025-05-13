package com.kyungbae.sbatis.aop;

// 모든 Controller 측의 모든 method 가 동작되기 전에 log 출력

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@Aspect
public class BeforeLoggingAdvice {

    // 포인트 컷 표현식과 포인트 컷 서명을 분리하는 방법
    // 포인트 컷
    @Pointcut("execution(* com.kyungbae.sbatis.controller.*Controller.*(..))")
    public void setPointCut(){}

    // 실행시킬 Advice 메소드 (공통로직)
    @Before("setPointCut()")
    public void beforAdvice(JoinPoint joinPoint){
        log.debug("beforeLoggingAdvice 실행💨💨💨💨💨💨💨💨");

        Object[] args = joinPoint.getArgs(); // 핵심로직메소드로 전달된 인자값(배열)
        for (Object a : args) {
            log.debug("args type: {}, value: {}", a.getClass().getSimpleName(), a);
        }

        Signature signature = joinPoint.getSignature();
        log.debug("method: {}", signature.getName()); // 메소드명
        log.debug("short method: {}", signature.toShortString()); // 클래스명.메소드명()
        log.debug("long method: {}", signature.toLongString()); // 풀네임
    }

}
