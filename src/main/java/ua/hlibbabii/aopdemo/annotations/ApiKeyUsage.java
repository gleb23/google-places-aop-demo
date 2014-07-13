package ua.hlibbabii.aopdemo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hlib on 7/6/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiKeyUsage {
    ApiType value();

    enum ApiType {
        PLACES,
        DIRECTIONS;
    }
}
