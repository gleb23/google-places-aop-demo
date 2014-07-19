package ua.hlibbabii.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import ua.hlibbabii.aopdemo.annotations.ApiKeyUsage;
import ua.hlibbabii.aopdemo.common.PropertiesFilesUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hlib on 7/6/14.
 */
@Aspect
public class ApiQuotaAspect {

    @Autowired
    private PropertiesFilesUtils propertiesFilesUtils;

    private final int PLACES_API_QUOTA = 1000;
    private final int DIRECTIONS_API_QUOTA = 1000;
    private final String PROPERTIES_FILE_NAME = "api-key.properties";

    @Before(" @annotation(ua.hlibbabii.aopdemo.annotations.ApiKeyUsage) ")
    public void logBefore1(JoinPoint joinPoint) throws IOException, URISyntaxException {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        ApiKeyUsage annotation = signature.getMethod().getAnnotation(ApiKeyUsage.class);
        ApiKeyUsage.ApiType apiType = annotation.value();
        Properties props = propertiesFilesUtils.loadProperties(PROPERTIES_FILE_NAME);

        String lastUpdated = props.getProperty("lastUpdated");
        String placesApiRequestsLeft = props.getProperty("placesApiRequestsLeft");
        String directionsApiRequestsLeft = props.getProperty("directionsApiRequestsLeft");

        System.out.println(lastUpdated);
        System.out.println(placesApiRequestsLeft);
        System.out.println(directionsApiRequestsLeft);

        lastUpdated = new Date().toString();
        props.put("lastUpdated", lastUpdated) ;
        if (apiType == ApiKeyUsage.ApiType.PLACES) {
            placesApiRequestsLeft = Integer.toString(Integer.parseInt(placesApiRequestsLeft) - 1);
            props.put("placesApiRequestsLeft", placesApiRequestsLeft);
        } else if (apiType == ApiKeyUsage.ApiType.DIRECTIONS) {
            directionsApiRequestsLeft = Integer.toString(Integer.parseInt(directionsApiRequestsLeft) - 1);
            props.put("directionsApiRequestsLeft", directionsApiRequestsLeft);
        } else {
            throw new AssertionError();
        }

        propertiesFilesUtils.saveProperties(props, PROPERTIES_FILE_NAME);
    }

    @Before(" execution(public String *.getRequestString())")
    public void logRequest(JoinPoint joinPoint) {
        //joi
        System.out.println("hijacked : " + joinPoint.getSignature().toLongString());
        System.out.println("******");
    }

    public PropertiesFilesUtils getPropertiesFilesUtils() {
        return propertiesFilesUtils;
    }

    public void setPropertiesFilesUtils(PropertiesFilesUtils propertiesFilesUtils) {
        this.propertiesFilesUtils = propertiesFilesUtils;
    }
}

// not reflection but poincut definition
// hijack get request
// add && !set*  && !get
// within code & call - write to presentation that spring supports ony withing and execution and find the rest words
// try to compile that shit
// razobratsia with props
// slide: aspectj lang example
// slide: versions of aspectj
// interfaces before after
// xml config
// types of veawing