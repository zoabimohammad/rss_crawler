package com.outbrain.rss.service;

import java.util.List;

public interface GenericService<E, K> {
    E add(final E entity);
    List<E> add(List<E> entities);
    void remove(final K key);
    void remove(final List<K> keys);
    E find(final K key);
    E update(final E entity);
    List<E> update(List<E> entities);
    List<E> findAll();
}
