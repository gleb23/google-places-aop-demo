package ua.hlibbabii.aopdemo.places.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ua.hlibbabii.aopdemo.places.persistence.Place;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlaceDaoImpl extends GenericDao<Place> implements PlaceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Place getByName(String query) {
        TypedQuery<Place> namedQuery = em.createNamedQuery("getPlaceByName", Place.class);
        namedQuery.setParameter("query", "%" + query + "%");
        List<Place> place = namedQuery.getResultList();
        return (place.isEmpty()) ? null : place.get(0);
    }
}
