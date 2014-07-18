package ua.hlibbabii.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import ua.hlibbabii.aopdemo.common.PropertiesFilesUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hlib on 7/8/14.
 */
@Aspect
public class AverageRequestTimeAspect {

    @Autowired
    private PropertiesFilesUtils propertiesFilesUtils;

    private AtomicInteger nRequests = new AtomicInteger(0);
    private AtomicLong requestsDurationSum = new AtomicLong(0);

    @Pointcut("execution (public * ua.hlibbabii.aopdemo.places.controllers.PlaceController.*(..) )")
    public void aroundPlaceControllerMethods() {}

    @Pointcut("execution (public * ua.hlibbabii.aopdemo.directions.controllers.DistanceController.*(..) )")
    public void aroundDistanceControllerMethods() {}

    @Around("aroundPlaceControllerMethods() || aroundDistanceControllerMethods()")
    public Object trackRequestDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        requestsDurationSum.addAndGet(end - start);
        nRequests.incrementAndGet();

        System.out.println("Requests: " + nRequests);
        if (nRequests.get() > 0) {
            System.out.println("Average request duration:" + requestsDurationSum.get() / nRequests.get());
        }
        return result;
    }

    public PropertiesFilesUtils getPropertiesFilesUtils() {
        return propertiesFilesUtils;
    }

    public void setPropertiesFilesUtils(PropertiesFilesUtils propertiesFilesUtils) {
        this.propertiesFilesUtils = propertiesFilesUtils;
    }
}
