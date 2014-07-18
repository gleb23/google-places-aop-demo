package ua.hlibbabii.aopdemo.aspects.withinterfaces;

import org.springframework.aop.ThrowsAdvice;
import ua.hlibbabii.aopdemo.places.services.GoogleRequestFailException;

/**
 * Created by hlib on 7/19/14.
 */

public class RequestFailProcessor implements ThrowsAdvice {

    public void  afterThrowing(GoogleRequestFailException ex) throws Throwable {
        System.out.println(ex.getStatus());
    }
}
