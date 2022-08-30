package com.lgadetsky.orderservice.service;

/**
 * Generic service
 * 
 * @author Leonid Gadetsky
 * @param <T> - object type 
 * @param <E> - object id type
 */
public interface Service<T, E> {
    E create(T obj);
    T findById(E id);
    E update(T obj);
    E deleteById(E id);
}
