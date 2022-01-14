package com.lyubchenko.springbootapplicationhomework8.service;

import java.util.List;
import java.util.UUID;

public interface Crud<T> {


    void create(T entity);

    List<T> read();

    void update(T entity);

    void deleteEntity(T entity);

    T getEntityById(UUID id);

}
