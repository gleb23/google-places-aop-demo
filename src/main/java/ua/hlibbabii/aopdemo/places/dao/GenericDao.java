package ua.hlibbabii.aopdemo.places.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public abstract class GenericDao<T> {

    private Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GenericDao() {
    }

    public GenericDao(Class<T> c) {
        this.clazz = c;
    }

    @PersistenceContext
    private EntityManager em;

    public List<T> getAll() {

        String queryString = "SELECT c FROM " + clazz.getSimpleName() + " c";
        TypedQuery<T> query =
                em.createQuery(queryString, clazz);
        List<T> resultList = query.getResultList();

        return resultList;
    }

    public T getById(Long id) {

        T c = (T) em.find(clazz, id);

        return c;
    }

    abstract public T getByName(String query);




    public boolean delete(Long id) {

        em.remove(em.getReference(clazz, id));
        return true;
    }

    public T insert(T obj) {
        em.persist(obj);
        return obj;
    }

    public T update(T obj) {

        return em.merge(obj);
    }
}
