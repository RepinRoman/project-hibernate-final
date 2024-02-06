package com.repin.dao;

import com.repin.domain.City;
import org.hibernate.SessionFactory;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public City getById(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM City c JOIN FETCH c.country WHERE c.id = :ID", City.class)
                .setParameter("ID", id)
                .getSingleResult();
    }

    public List<City> getItems(int offset, int limit) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM City c", City.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public int getTotalCount() {
        return Math.toIntExact(sessionFactory.getCurrentSession()
                .createQuery("SELECT count(c) FROM City c", Long.class)
                .uniqueResult());
    }
}