//package ua.hlibbabii.aopdemo.aspects;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.joda.time.DateTime;
//import ua.hlibbabii.aopdemo.annotations.ApiKeyUsage;
//
//import java.io.*;
//import java.net.URISyntaxException;
//import org.slf4j.Logger;
//
//import javax.annotation.PostConstruct;
//
///**
//* Created by hlib on 7/6/14.
//*/
//@Aspect
//public class ApiQuotaAspect {
//
//    private final int PLACES_API_QUOTA = 1000;
//    private final int DIRECTIONS_API_QUOTA = 1000;
//
//    private DateTime lastUpdated = null;
//    private int placesApiRequestsLeft = 0;
//    private int directionsApiRequestsLeft = 0;
//
//
//    @Before(" @annotation(apiKeyUsage) ")
//    synchronized public void trackApiKeyUsage(JoinPoint joinPoint, ua.hlibbabii.aopdemo.annotations.ApiKeyUsage apiKeyUsage)
//            throws IOException, URISyntaxException {
//
//        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
//        ApiKeyUsage annotation = signature.getMethod().getAnnotation(ApiKeyUsage.class);
//        ApiKeyUsage.ApiType apiType = annotation.value();
//
//        DateTime currentDateTime = new DateTime();
//        if (lastUpdated == null || lastUpdated.getYear() != currentDateTime.getYear() ||
//                lastUpdated.getDayOfYear() != currentDateTime.getDayOfYear()) {
//            placesApiRequestsLeft = 0;
//            directionsApiRequestsLeft = 0;
//        }
//        lastUpdated = currentDateTime;
//
//        if (apiType == ApiKeyUsage.ApiType.PLACES) {
//            ++placesApiRequestsLeft;
//        } else if (apiType == ApiKeyUsage.ApiType.DIRECTIONS) {
//            ++directionsApiRequestsLeft;
//        } else {
//            throw new AssertionError();
//        }
//
//        org.slf4j.LoggerFactory.getLogger(joinPoint.getTarget().getClass()).info(String.format(
//                "\n ApiKey was last used: %s, " +
//                        "\n Usage for today" +
//                        "\n -------------------------------------------" +
//                        "\n Google Places: %d / %d " +
//                        "\n Google Directions: %d / %d" +
//                        "\n -------------------------------------------",
//                lastUpdated.toString(),
//                placesApiRequestsLeft, PLACES_API_QUOTA,
//                directionsApiRequestsLeft, DIRECTIONS_API_QUOTA));
//    }
//
//    @AfterThrowing(" @annotation(apiKeyUsage) ")
//    public void g(JoinPoint joinPoint, ua.hlibbabii.aopdemo.annotations.ApiKeyUsage apiKeyUsage) {
//        org.slf4j.LoggerFactory.getLogger(joinPoint.getTarget().getClass()).error("No connection!");
//    }
//}