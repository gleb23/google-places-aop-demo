package ua.hlibbabii.aopdemo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hlib on 7/7/14.
 */
@Component
public enum ApiKey {
    INSTANCE;
    private static final String API_KEY_PROPERTIES_FILE_NAME = "api-key.properties";

    @Autowired
    private PropertiesFilesUtils propertiesFilesUtils;

    private String value;

    public String getValue() {
        return value;
    }

    ApiKey() {
        try {
            value = propertiesFilesUtils.loadProperties(API_KEY_PROPERTIES_FILE_NAME)
                .getProperty("apiKey");
        } catch (IOException e) {
            new RuntimeException("Failed to get API key");
        }
    }
}