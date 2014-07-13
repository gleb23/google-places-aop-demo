package ua.hlibbabii.aopdemo.places.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ua.hlibbabii.aopdemo.places.persistence.Place;
import ua.hlibbabii.aopdemo.places.services.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private PlaceService placeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Place> getPlaces() {
        List<Place> allPlaces = placeService.getAll();

        LoggerFactory.getLogger(PlaceController.class).debug(allPlaces.toString());

        return allPlaces;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Place getPlace(@PathVariable Long id) {
        return placeService.getById(id);
    }

    /**
     * returns place either from db or sends request to google places API to get place. Place retrieved from
     * google then stored in db
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public Place createPlaceFromGoogle(@PathVariable String query) {
        return placeService.searchPlace(query);
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String createPlaceFromGoogle(@PathVariable Long id) {
        String details =  placeService.getDetails(id);
        if (details != null) {
            return details;
        } else {
            return "Not found";
        }
    }



    @RequestMapping(method = RequestMethod.POST)
    public Place createPlace(Place place) {
        return placeService.insert(place);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Place updatePlace(Place place) {
        return placeService.update(place);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deletePlace(@PathVariable Long id) {
        return placeService.delete(id);
    }

    //setters - getters

    public PlaceService getPlaceService() {
        return placeService;
    }

    public void setPlaceService(PlaceService PlaceService) {
        this.placeService = PlaceService;
    }
}
