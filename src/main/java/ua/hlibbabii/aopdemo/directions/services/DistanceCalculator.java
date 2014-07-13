package ua.hlibbabii.aopdemo.directions.services;

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
public class DistanceCalculator {

    private DirectionsResponseParser directionsResponseParser;

    private String getCommaSeparatedCoordinates(Place place) {
        return place.getLongitude() + "," +place.getLatitude();
    }

    private String getRequest(Place from, Place to) {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost("maps.googleapis.com")
                .setPath("/maps/api/directions/json")
                        // params
                .setParameter("origin", getCommaSeparatedCoordinates(from))
                .setParameter("destination", getCommaSeparatedCoordinates(to))
                .setParameter("mode", "transit")
                .setParameter("departure_time", Long.toString(System.currentTimeMillis()))
                .setParameter("key", ApiKey.API_KEY);

        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Bad uri: %s", uri.toString()));
        }
        String request = uri.toASCIIString();
        return request;
    }

    @ApiKeyUsage(ApiKeyUsage.ApiType.DIRECTIONS)
    public int calculateTimeToGet(Place from, Place to) {
        String response = new RestTemplate().getForObject(getRequest(from, to), String.class);
        return directionsResponseParser.getTimeInMinutes(response);
    }

    public DirectionsResponseParser getDirectionsResponseParser() {
        return directionsResponseParser;
    }

    public void setDirectionsResponseParser(DirectionsResponseParser directionsResponseParser) {
        this.directionsResponseParser = directionsResponseParser;
    }
}
