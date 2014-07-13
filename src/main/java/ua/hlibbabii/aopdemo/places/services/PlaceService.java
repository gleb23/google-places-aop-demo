package ua.hlibbabii.aopdemo.places.services;

import ua.hlibbabii.aopdemo.places.persistence.Place;

import java.util.List;


public interface PlaceService {
    public List<Place> getAll();

    public Place getById(Long id);

    public boolean delete(Long id);

    public Place insert(Place Place);

    public Place update(Place Place);

    public Place searchPlace(String query);

    String getDetails(Long id);
}
