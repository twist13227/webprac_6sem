package ru.msu.cmc.prac.DAOs;

import ru.msu.cmc.prac.classes.CommonClass;

import java.util.Collection;

public interface CommonClassDAO<T extends CommonClass<ID>, ID> {
    void save(T entity);
    void saveCollection(Collection<T> entities);
    void update(T entity);
    void delete(T entity);
    Collection<T> getAll();
    T getById(ID id);
    void deleteById(ID id);
}
