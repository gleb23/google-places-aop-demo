package ua.hlibbabii.aopdemo.places.dao;

import ua.hlibbabii.aopdemo.places.persistence.Place;

import java.util.List;

public interface PlaceDao {
    public List<Place> getAll();

    public Place getById(Long id);

    public boolean delete(Long id);

    public Place insert(Place Place);

    public Place update(Place Place);

    public Place getByName(String query);
}
