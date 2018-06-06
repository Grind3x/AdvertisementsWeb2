package com.gmail.grind3x.dao.Impl;

import com.gmail.grind3x.dao.CategoryDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Advertisement;
import com.gmail.grind3x.model.Author;
import com.gmail.grind3x.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class CategoryDAODBImpl implements CategoryDAO {
    private EntityManager em;

    public CategoryDAODBImpl() {
        em = PostgresDAOFactory.getEntityManager();

    }

    @Override
    public List<Category> getAll() {
        List<Category> list = null;
        try {
            Query query = em.createQuery("SELECT c FROM Category c", Category.class);
            list = (List<Category>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return list;
    }

    @Override
    public void insert(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Category category) {
        em.getTransaction().begin();
        em.remove(category);
        em.getTransaction().commit();
    }

    @Override
    public Category findByName(String name) {
        Category category = null;
        try {
            Query query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", name);
            category = (Category) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
        return category;
    }

    @Override
    public Category findById(Long id) {
        Category category = null;
        try {
            Query query = em.createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class);
            query.setParameter("id", id);
            category = (Category) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
        return category;
    }

    @Override
    public void update(Category category) {
        em.getTransaction().begin();
        em.refresh(category);
        em.getTransaction().commit();
    }
}
