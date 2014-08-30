package ua.hlibbabii.aopdemo.places.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hlibbabii.aopdemo.places.dao.GenericDao;
import ua.hlibbabii.aopdemo.places.dao.PlaceDao;
import ua.hlibbabii.aopdemo.places.persistence.Place;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private PlaceDao placeDao;

    private GooglePlaceLoader googlePlaceLoader;

    private GooglePlaceDetailsLoader googlePlaceDetailsLoader;


    @Override
    public List<Place> getAll() {
        return placeDao.getAll();
    }

    @Override
    public Place getById(Long id) {
        return placeDao.getById(id);
    }

    @Transactional
    @Override
    public Place searchPlace(String query) {
        Place place = placeDao.getByName(query);
        if (place == null) {
            place = googlePlaceLoader.loadFromGoogle(query);
            placeDao.insert(place);
        }
        return place;
    }

    @Override
    public String getDetails(Long id) {
        Place place =  placeDao.getById(id);
        if (place != null) {
            String googleId = place.getGoogleId();
            return googlePlaceDetailsLoader.getPlaceDetails(googleId);
        } else {
            return null;
        }
    }



    @Transactional
    @Override
    public Place insert(Place Place) {
        placeDao.insert(Place);
        return Place;
    }

    @Transactional
    @Override
    public Place update(Place Place) {
        placeDao.update(Place);
        return Place;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        return placeDao.delete(id);
    }


    // SETTERS, GETTERS

    public GooglePlaceDetailsLoader getGooglePlaceDetailsLoader() {
        return googlePlaceDetailsLoader;
    }

    public void setGooglePlaceDetailsLoader(GooglePlaceDetailsLoader googlePlaceDetailsLoader) {
        this.googlePlaceDetailsLoader = googlePlaceDetailsLoader;
    }

    public PlaceDao getPlaceDao() {
        return placeDao;
    }

    public void setPlaceDao(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    public GooglePlaceLoader getGooglePlaceLoader() {
        return googlePlaceLoader;
    }

    public void setGooglePlaceLoader(GooglePlaceLoader googlePlaceLoader) {
        this.googlePlaceLoader = googlePlaceLoader;
    }
}
