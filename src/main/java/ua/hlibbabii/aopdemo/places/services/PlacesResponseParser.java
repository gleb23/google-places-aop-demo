package ua.hlibbabii.aopdemo.places.services;

import org.json.JSONArray;
import org.json.JSONObject;
import ua.hlibbabii.aopdemo.places.persistence.Place;

/**
 * Created by hlib on 7/6/14.
 */
public class PlacesResponseParser {

    private static final String RESULTS_TAG = "results";
    private static final String NAME_TAG = "name";
    private static final String PLACE_ID_TAG = "place_id";
    private static final String GEOMETRY_TAG = "geometry";
    private static final String LOCATION_TAG = "location";
    private static final String LONGITUDE_TAG = "lng";
    private static final String LATITUDE_TAG = "lat";

    public Place getFirstPlace(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        checkStatus(jsonResponse);

        JSONArray jsonPlaces = jsonResponse.getJSONArray(RESULTS_TAG);
        if (jsonPlaces.length() > 0) {
            JSONObject jsonPlace = jsonPlaces.getJSONObject(0);
            return parsePlace(jsonPlace);
        } else {
            return null;
        }
    }

    private Place parsePlace(JSONObject jsonPlace) {
        Place place = new Place();

        place.setName(jsonPlace.getString(NAME_TAG));

        if (jsonPlace.has(GEOMETRY_TAG)) {
            JSONObject geometry = jsonPlace.getJSONObject(GEOMETRY_TAG);
            if (geometry.has(LOCATION_TAG)) {
                JSONObject location = geometry.getJSONObject(LOCATION_TAG);
                double longitude = location.getDouble(LONGITUDE_TAG);
                place.setLongitude(longitude);
                double latitude = location.getDouble(LATITUDE_TAG);
                place.setLatitude(latitude);
            }
        }
        place.setGoogleId(jsonPlace.getString(PLACE_ID_TAG));

        return place;
    }

    private void checkStatus(JSONObject jsonResponse) {
        String responseStatus = jsonResponse.getString("status");
        if (!responseStatus.equals("ZERO_RESULTS")) {

        } else if (!responseStatus.equals("OK") && (!responseStatus.equals("ZERO_RESULTS"))) {
            throw new GoogleRequestFailException(responseStatus);
        }
    }

}
