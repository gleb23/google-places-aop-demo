package ua.hlibbabii.aopdemo.places.services;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;
import ua.hlibbabii.aopdemo.annotations.ApiKeyUsage;
import ua.hlibbabii.aopdemo.common.ApiKey;
import ua.hlibbabii.aopdemo.places.persistence.Place;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by hlib on 7/6/14.
 */
public class GooglePlaceLoader {

    public final String KIEV_LONGITUDE = "30.5233";
    public final String KIEV_LATITUDE = "50.4500";

    private RestTemplate restTemplate;

    private PlacesResponseParser placesResponseParser;

    private String getRequestString(String query) {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost("maps.googleapis.com")
                .setPath("/maps/api/place/textsearch/json")
                        // params
                .setParameter("key", ApiKey.INSTANCE.getValue())
                .setParameter("query", query + " Kiev");

        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Bad uri: %s", uri.toString()));
        }
        String request = uri.toASCIIString();
        return request;
    }

    @ApiKeyUsage(ApiKeyUsage.ApiType.PLACES)
    public Place loadFromGoogle(String query) {
        String response = restTemplate.getForObject(getRequestString(query), String.class);
        return placesResponseParser.getFirstPlace(response);
    }

    // SETTERS, GETTERS

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlacesResponseParser getPlacesResponseParser() {
        return placesResponseParser;
    }

    public void setPlacesResponseParser(PlacesResponseParser placesResponseParser) {
        this.placesResponseParser = placesResponseParser;
    }
}
