package ua.hlibbabii.aopdemo.directions.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.hlibbabii.aopdemo.directions.services.DistanceCalculator;
import ua.hlibbabii.aopdemo.directions.services.NoRoutesException;
import ua.hlibbabii.aopdemo.places.persistence.Place;
import ua.hlibbabii.aopdemo.places.services.PlaceService;

/**
 * Created by hlib on 7/6/14.
 */
@RestController
@RequestMapping("/time-to-get")
public class DistanceController {

    DistanceCalculator distanceCalculator;

    PlaceService placeService;

    @RequestMapping(value = "/{from}/{to}", method = RequestMethod.GET)
    public String getPlace(@PathVariable Long from, @PathVariable Long to) {
        Place placeFrom = placeService.getById(from);
        Place placeTo = placeService.getById(to);
        if (placeFrom == null || placeTo == null) {
            return "One of locations is not found!";
        }
        String result;
        try {
            result = String.valueOf(distanceCalculator.calculateTimeToGet(placeFrom, placeTo));
        } catch (NoRoutesException e) {
            result = "No routes available!";
        }
        return result;
    }

    public DistanceCalculator getDistanceCalculator() {
        return distanceCalculator;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public PlaceService getPlaceService() {
        return placeService;
    }

    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }
}
