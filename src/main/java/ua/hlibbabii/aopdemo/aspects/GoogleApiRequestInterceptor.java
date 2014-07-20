package ua.hlibbabii.aopdemo.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import ua.hlibbabii.aopdemo.places.services.GoogleRequestFailException;

import java.lang.reflect.Method;

/**
 * Created by hlib on 7/19/14.
 */

public class GoogleApiRequestInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleApiRequestInterceptor.class);

    public void  afterThrowing(GoogleRequestFailException ex) throws Throwable {
        System.out.println(ex.getStatus());
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info(String.format("Response received: %s", returnValue));
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info(String.format("Request sent: %s", args[0]));
    }
}
