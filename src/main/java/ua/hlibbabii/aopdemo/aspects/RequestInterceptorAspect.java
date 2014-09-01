//package ua.hlibbabii.aopdemo.aspects;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.LoggerFactory;
//
///**
//* Created by hlib on 9/1/14.
//*/
//@Aspect
//public class RequestInterceptorAspect {
//
//    @Pointcut("execution(* *.getRequestString(..))")
//    private void getRequestPointcut(){}
//
//    @Around("getRequestPointcut()")
//    public Object something(ProceedingJoinPoint pjp) throws Throwable {
//        Object result = pjp.proceed();
//        LoggerFactory.getLogger(pjp.getTarget().getClass()).info("Request sent: %s", result);
//        return result;
//    }
//}
