package com.lgadetsky.orderservice.service;

/**
 * @author Leonid Gadetsky
 */
public interface Service<T, E> {
    T create(T obj);
    T findById(E id);
    T update(T obj);
    void deleteById(E id);
}
