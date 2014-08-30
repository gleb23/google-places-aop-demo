package ua.hlibbabii.aopdemo.places.services;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;
import ua.hlibbabii.aopdemo.common.ApiKey;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by hlib on 7/7/14.
 */
public class GooglePlaceDetailsLoader {
    private String getRequestString(String googleId) {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost("maps.googleapis.com")
                .setPath("/maps/api/place/details/json")
                        // params
                .setParameter("key", ApiKey.INSTANCE.getValue())
                .setParameter("placeid", googleId);

        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Bad uri: %s", uri.toString()));
        }
        String request = uri.toASCIIString();
        return request;
    }

    public String getPlaceDetails(String googleId) {
        return new RestTemplate().getForObject(getRequestString(googleId), String.class);
    }
}
