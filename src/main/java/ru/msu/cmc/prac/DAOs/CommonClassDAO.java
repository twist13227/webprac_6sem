package ru.msu.cmc.prac.DAOs;

import java.util.Collection;

public interface CommonClassDAO<T> {
    void save(T entity);
    void saveCollection(Collection<T> entities);
    void update(T entity);
    void delete(T entity);
    Collection<T> getAll();
    T getById(Integer id);
    void deleteById(Integer id);
}
