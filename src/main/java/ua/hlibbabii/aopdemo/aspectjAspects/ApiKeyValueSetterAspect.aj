package ua.hlibbabii.aopdemo.aspectjAspects;

import ua.hlibbabii.aopdemo.common.ApiKey;

/**
* Created by hlib on 9/1/14.
*/
public aspect ApiKeyValueSetterAspect {
    pointcut apiValueSetter(): set(String ua.hlibbabii.aopdemo.common.ApiKey.value);

    before(): apiValueSetter() {
        System.out.println("Setting ApiKey value!");
    }
}
