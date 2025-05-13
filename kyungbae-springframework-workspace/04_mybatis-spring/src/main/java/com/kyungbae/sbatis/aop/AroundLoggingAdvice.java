package com.kyungbae.sbatis.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class AroundLoggingAdvice {

    // pointcut 한번에
    @Around("execution(* com.kyungbae.sbatis.controller.*Controller.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 핵심 로직 메소드가 실행되기 전 시점
//        log.debug("핵심로직 실행 전 ");
        // 요청 파리미터, 요청전송방식, 요청주소 출력
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        // 요청 파라미터 출력
        Map<String, String[]> map = request.getParameterMap(); // string 배열 {key=["value"], key=["value", "value"]} 로 반환

        String params ="";
        if (map.isEmpty()) {
            params += "No Parameter";
        } else {
            for (String key : map.keySet()) {
                String[] values = map.get(key);
                params += key + ":" + Arrays.toString(values) + " ";
            }
        }
        String method = request.getMethod();
        String requestUri = request. getRequestURI();
        int textLength = Math.max((method+requestUri).length(), params.length())+3;
        log.debug("{}{}{}", "┌","─".repeat(textLength),"┐");
        // 요청전송방식 | 요청주소방식 출력
        log.debug(" {} │ {}", request.getMethod(), request.getRequestURI());
        // 요청파라미터 출력
        log.debug(" {}", params);

        Object obj = proceedingJoinPoint.proceed(); // 핵심로직메소드를 직접 실행

        // 핵심 로직 메소드가 실행된 후 시점
//        log.debug("핵심로직 실행 후");
        log.debug("{}{}{}","└", "─".repeat(textLength),"┘");



        return obj;
    }

}
