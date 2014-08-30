package ua.hlibbabii.aopdemo.directions.services;

import org.json.JSONObject;

/**
 * Created by hlib on 7/6/14.
 */
public class DirectionsResponseParser {
    public int getTimeInMinutes(String response) throws NoRoutesException {
        JSONObject jsonResponse = new JSONObject(response);

        checkStatus(jsonResponse);

        JSONObject route = jsonResponse.getJSONArray("routes").getJSONObject(0);
        int duration = route.getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getInt("value");
        return duration;
    }

    private void checkStatus(JSONObject jsonResponse) throws NoRoutesException {
        String responseStatus = jsonResponse.getString("status");
        if (responseStatus.equals("NOT_FOUND")) {
            throw new NoRoutesException();
        } else if (!responseStatus.equals("OK")) {
            throw new RuntimeException(responseStatus);
        }
    }
}
