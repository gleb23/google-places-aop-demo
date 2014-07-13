package ua.hlibbabii.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;

/**
 * Created by hlib on 7/8/14.
 */
@Aspect
public class LoggingAspect {

    // TODO for all setters and gettters
    @Pointcut(" execution(* ua.hlibbabii.aopdemo.places.dao.GenericDao.getClazz()) &&" +
            " execution(* ua.hlibbabii.aopdemo.places.dao.GenericDao.setClazz(..))")
    public void gettersSettersPointcut(){}

    @Pointcut(" execution(* ua.hlibbabii.aopdemo.places.dao.GenericDao.*(..))")
    public void daoMethodsPointcut(){}

    @Pointcut(" execution(* ua.hlibbabii.aopdemo.places.controllers.PlaceController.*(..))")
    public void controllerMethodsPointcut(){}

    @Pointcut(" within(ua.hlibbabii.aopdemo.places.controllers.PlaceController) ")
    public void withinControllerClassPointcut(){}

    @Before("daoMethodsPointcut() && ! gettersSettersPointcut()")
    public void logDao(JoinPoint joinPoint) {
        LoggerFactory.getLogger(joinPoint.getTarget().getClass()).info(
                String.format("Calling dao method %s", joinPoint.getSignature().toShortString())
        );
    }

    @Before("controllerMethodsPointcut()")
    public void logController(JoinPoint joinPoint) {
        LoggerFactory.getLogger(joinPoint.getTarget().getClass()).info(
                String.format("Calling controller method %s", joinPoint.getSignature().toShortString())
        );
    }
}
