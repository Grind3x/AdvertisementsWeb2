package com.gmail.grind3x.dao.Impl;

import com.gmail.grind3x.dao.ImageDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Image;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class ImageDAODBImpl implements ImageDAO {
    private EntityManager em;

    public ImageDAODBImpl() {
        em = PostgresDAOFactory.getEntityManager();
    }

    @Override
    public void insertImage(Image image) {
        em.getTransaction().begin();
        em.persist(image);
        em.getTransaction().commit();
    }

    @Override
    public void deleteImage(Image image) {
        em.getTransaction().begin();
        em.remove(image);
        em.getTransaction().commit();
    }

    @Override
    public Image findImageByName(String name) {
        Image image = null;
        try {
            Query query = em.createQuery("SELECT i FROM Image i WHERE i.name = :name", Image.class);
            query.setParameter("name", name);
            image = (Image) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
        return image;
    }

    @Override
    public void updateImage(Image image) {
        em.getTransaction().begin();
        em.refresh(image);
        em.getTransaction().commit();
    }
}
