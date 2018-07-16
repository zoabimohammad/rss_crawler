package com.outbrain.rss.dao;

import java.util.List;

public interface GenericDAO<E,K> {
    List<E> add(final List<E> entities);
    List<E> update(final List<E> entities);
    void remove(final List<K> keys);
    E add(final E entity);
    void remove(final K key);
    E find(final K key);
    E update(final E entity);
    List<E> findAll();
}
