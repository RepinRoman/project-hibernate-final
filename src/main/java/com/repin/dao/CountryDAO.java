package com.repin.dao;

import com.repin.domain.Country;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Country c JOIN FETCH c.languages", Country.class)
                .list();
    }
}