package com.lgadetsky.orderservice.service;

/**
 * Generic service
 * 
 * @author Leonid Gadetsky
 * @param <T> - object type 
 * @param <E> - object id type
 */
public interface Service<T, E> {
    T create(T obj);
    T findById(E id);
    T update(T obj);
    void deleteById(E id);
}
