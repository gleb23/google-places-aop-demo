package ua.hlibbabii.aopdemo.aspectjAspects;

import org.slf4j.LoggerFactory;

/**
* Created by hlib on 8/31/14.
*/
public aspect RequestInterceptorAspect {

    pointcut getRequestString(): execution(* *.getRequestString(..));

    Object around(): getRequestString() {
        Object result = proceed();
        LoggerFactory.getLogger(thisJoinPoint.getTarget().getClass())
                .info(String.format("Request sent: %s", result));
        return result;
    }
}
