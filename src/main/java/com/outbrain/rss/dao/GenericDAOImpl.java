package com.outbrain.rss.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class GenericDAOImpl <E, K> extends JdbcDaoSupport implements GenericDAO<E, K> {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private DataSource ds;

    // Entity type
    private Class<E> type;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @PostConstruct
    private void initialize() {
        super.setDataSource(ds);
    }

    @Override
    public E find(final K key) {
        return (E) entityManager.find(type, key);
    }


    @Override
    public E add(final E entity) {
        // Persist and return
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void remove(final K key) {
        // Remove
        entityManager.remove(entityManager.getReference(type, key));
    }

    @Override
    public E update(final E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery<E> cq = entityManager.getCriteriaBuilder().createQuery(type);
        Root<E> rootEntry = cq.from(type);
        return entityManager.createQuery(cq.select(rootEntry)).getResultList();
    }

    @Override
    public List<E> add(final List<E> entities) {
        final int BATCH_SIZE = 50;

        // Perform Insertion in batches
        for (int i = 0; i < entities.size(); i++) {
            entityManager.persist(entities.get(i));

            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        entityManager.flush();
        entityManager.clear();

        return entities;
    }

    @Override
    public List<E> update(final List<E> entities) {
        final int BATCH_SIZE = 50;

        // Perform Insertion in batches
        for (int i = 0; i < entities.size(); i++) {
            entityManager.merge(entities.get(i));

            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        entityManager.flush();
        entityManager.clear();

        return entities;
    }

    @Override
    public void remove(final List<K> keys) {
        final int BATCH_SIZE = 50;

        // Perform Insertion in batches
        for (int i = 0; i < keys.size(); i++) {
            entityManager.remove(keys.get(i));

            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        entityManager.flush();
        entityManager.clear();
    }
}