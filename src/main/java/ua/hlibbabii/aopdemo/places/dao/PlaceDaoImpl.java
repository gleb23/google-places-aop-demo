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

//    public List<Place> getAll() {
//
//        String queryString = "SELECT c FROM " + Place.class.getSimpleName() + " c";
//        TypedQuery<Place> query =
//                em.createQuery(queryString, Place.class);
//        List<Place> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    public Place getById(Long id) {
//        Place c = (Place) em.find(Place.class, id);
//        return c;
//    }
//
//    public boolean delete(Long id) {
//        //try {
//            em.remove(em.getReference(Place.class, id));
//        //} catch (EntityNotFoundException ex) {return false;}
//        return true;
//    }
//
//    public Place insert(Place obj) {
//        em.persist(obj);
//        return obj;
//    }
//
//    public Place update(Place obj) {
//        return em.merge(obj);
//    }

    @Override
    public Place getByName(String query) {
        TypedQuery<Place> namedQuery = em.createNamedQuery("getPlaceByName", Place.class);
        namedQuery.setParameter("query", "%" + query + "%");
        List<Place> place = namedQuery.getResultList();
        return (place.isEmpty()) ? null : place.get(0);
    }
}
