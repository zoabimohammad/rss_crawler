package com.outbrain.rss.service;

import com.outbrain.rss.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {
    @Autowired
    private GenericDAO<E, K> genericDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E add(E entity) {
        return genericDAO.add(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<E> add(List<E> entities) {
        return genericDAO.add(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(K key) {
        genericDAO.remove(key);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E find(K key) {
        return genericDAO.find(key);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E update(E entity) {
        return genericDAO.update(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<E> update(List<E> entities) {
        return genericDAO.update(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> findAll() {
        return genericDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(final List<K> keys) {
        genericDAO.remove(keys);
    }
}

