package ua.hlibbabii.aopdemo.aspectjAspects;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hlib on 9/1/14.
 */
public aspect AverageRequestTimeAspect {

    private AtomicInteger nRequests = new AtomicInteger(0);
    private AtomicLong requestsDurationSum = new AtomicLong(0);

    private pointcut placeControllerMethods():
        execution(public * ua.hlibbabii.aopdemo.places.controllers.PlaceController.*(..) ) ;

    private pointcut distanceControllerMethods():
            execution (public * ua.hlibbabii.aopdemo.directions.controllers.DistanceController.*(..) ) ;

    private pointcut distancePlaceControllerMethods():
            placeControllerMethods() || distanceControllerMethods();

    Object around(): placeControllerMethods() || distanceControllerMethods() {
        long start = System.currentTimeMillis();

        Object result = proceed();

        long end = System.currentTimeMillis();
        requestsDurationSum.addAndGet(end - start);
        nRequests.incrementAndGet();

        System.out.println("Requests: " + nRequests);
        if (nRequests.get() > 0) {
            System.out.println("Average request duration:" + requestsDurationSum.get() / nRequests.get());
        }
        return result;
    }
}
