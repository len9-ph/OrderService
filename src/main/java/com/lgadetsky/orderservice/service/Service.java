package com.lgadetsky.orderservice.service;

/**
 * @author Leonid Gadetsky
 */
public interface Service<T, E> {
    E create(T obj);
    T findById(E id);
    E update(T obj);
    E deleteById(E id);
}
