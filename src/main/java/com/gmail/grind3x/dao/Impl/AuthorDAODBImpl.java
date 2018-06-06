package com.gmail.grind3x.dao.Impl;

import com.gmail.grind3x.dao.AuthorDAO;
import com.gmail.grind3x.dao.PostgresDAOFactory;
import com.gmail.grind3x.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


public class AuthorDAODBImpl implements AuthorDAO {
    private EntityManager em;

    public AuthorDAODBImpl() {
        em = PostgresDAOFactory.getEntityManager();
    }

    @Override
    public void insertAuthor(Author author) {
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
    }

    @Override
    public void deleteAuthor(Author author) {
        em.getTransaction().begin();
        em.remove(author);
        em.getTransaction().commit();
    }

    @Override
    public Author findAuthorByName(String name) {
        Author author = null;
        try {
            Query query = em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
            query.setParameter("name", name);
            author = (Author) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        em.getTransaction().begin();
        em.refresh(author);
        em.getTransaction().commit();
    }
}
