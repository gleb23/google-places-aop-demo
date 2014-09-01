//package ua.hlibbabii.aopdemo.aspects;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
//* Created by hlib on 7/21/14.
//*/
//public class AverageRequestTimeAspectWithXml {
//
//    private AtomicInteger nRequests = new AtomicInteger(0);
//    private AtomicLong requestsDurationSum = new AtomicLong(0);
//
//    public Object trackRequestDuration(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//
//        Object result = joinPoint.proceed();
//
//        long end = System.currentTimeMillis();
//        requestsDurationSum.addAndGet(end - start);
//        nRequests.incrementAndGet();
//
//        System.out.println("Requests: " + nRequests);
//        if (nRequests.get() > 0) {
//            System.out.println("Average request duration:" + requestsDurationSum.get() / nRequests.get());
//        }
//        return result;
//    }
//}
