package ua.hlibbabii.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import ua.hlibbabii.aopdemo.annotations.ApiKeyUsage;
import ua.hlibbabii.aopdemo.common.PropertiesFilesUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by hlib on 7/6/14.
 */
@Aspect
public class ApiQuotaAspect {

    @Autowired
    private PropertiesFilesUtils propertiesFilesUtils;

    private static String DATE_TEMPLATE = "EEE, d MMM yyyy HH:mm:ss Z";
    private final int PLACES_API_QUOTA = 1000;
    private final int DIRECTIONS_API_QUOTA = 1000;
    private final String PROPERTIES_FILE_NAME = "api-key.properties";

    private DateTime lastUpdated;
    private int placesApiRequestsLeft;
    private int directionsApiRequestsLeft;

    @PostConstruct
    public void init() throws IOException, ParseException {
        Properties props = propertiesFilesUtils.loadProperties(PROPERTIES_FILE_NAME);

        lastUpdated = DateTimeFormat.forPattern(DATE_TEMPLATE).parseDateTime(props.getProperty("lastUpdated"));
        placesApiRequestsLeft = Integer.parseInt(props.getProperty("placesApiRequestsLeft"));
        directionsApiRequestsLeft = Integer.parseInt(props.getProperty("directionsApiRequestsLeft"));
    }

    @PreDestroy
    public void tearDown() throws IOException, URISyntaxException {
        Properties props = new Properties();
        props.put("lastUpdated", DateTimeFormat.forPattern(DATE_TEMPLATE).print(lastUpdated));
        props.put("placesApiRequestsLeft", Integer.toString(placesApiRequestsLeft));
        props.put("directionsApiRequestsLeft", Integer.toString(directionsApiRequestsLeft));

        propertiesFilesUtils.saveProperties(props, PROPERTIES_FILE_NAME);
    }

    @Before(" @annotation(ua.hlibbabii.aopdemo.annotations.ApiKeyUsage) ")
    public void trackApiKeyUsage(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        ApiKeyUsage annotation = signature.getMethod().getAnnotation(ApiKeyUsage.class);
        ApiKeyUsage.ApiType apiType = annotation.value();

        System.out.println(lastUpdated);
        System.out.println(placesApiRequestsLeft);
        System.out.println(directionsApiRequestsLeft);

        DateTime currentDateTime = new DateTime();
        if (lastUpdated.getYear() != currentDateTime.getYear() ||
                lastUpdated.getDayOfYear() != currentDateTime.getDayOfYear()) {
            placesApiRequestsLeft = PLACES_API_QUOTA;
            directionsApiRequestsLeft = DIRECTIONS_API_QUOTA;
        }
        lastUpdated = currentDateTime;

        if (apiType == ApiKeyUsage.ApiType.PLACES) {
            --placesApiRequestsLeft;
        } else if (apiType == ApiKeyUsage.ApiType.DIRECTIONS) {
            --directionsApiRequestsLeft;
        } else {
            throw new AssertionError();
        }
    }

    @Before(" execution(public String *.getRequestString())")
    public void logRequest(JoinPoint joinPoint) {
        System.out.println("hijacked : " + joinPoint.getSignature().toLongString());
        System.out.println("******");
    }

    // getters, setters

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