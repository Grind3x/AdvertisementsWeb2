package com.gmail.grind3x.dao;

import com.gmail.grind3x.dao.Impl.AdvertisementDAODBImpl;
import com.gmail.grind3x.dao.Impl.AuthorDAODBImpl;
import com.gmail.grind3x.dao.Impl.CategoryDAODBImpl;
import com.gmail.grind3x.dao.Impl.ImageDAODBImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostgresDAOFactory {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPATest");
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public PostgresDAOFactory() {
    }

    public AuthorDAO getAuthorDAO() {
        return new AuthorDAODBImpl();
    }

    public AdvertisementDAO getAdvertisementDAO() {
        return new AdvertisementDAODBImpl();
    }

    public ImageDAO getImageDAO() {
        return new ImageDAODBImpl();
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAODBImpl();
    }
}
