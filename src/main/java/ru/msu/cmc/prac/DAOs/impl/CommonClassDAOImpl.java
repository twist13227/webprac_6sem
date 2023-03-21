package ru.msu.cmc.prac.DAOs.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.prac.DAOs.CommonClassDAO;
import ru.msu.cmc.prac.HibernateConfiguration;

import java.util.Collection;

@Repository
public abstract class CommonClassDAOImpl<T> implements CommonClassDAO<T> {
    protected Class<T> entityClass;

    public CommonClassDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    Session openSession() {
        return HibernateConfiguration.getSessionFactory().openSession();
    }

    @Override
    public void save(T entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveCollection(Collection<T> entities) {
        try (Session session = openSession()) {
            session.beginTransaction();
            for (T entity : entities) {
                this.save(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }
    @Override
    public Collection<T> getAll() {
        try (Session session = openSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.from(entityClass);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public T getById(Integer id) {
        try (Session session = openSession()) {
            return session.get(entityClass, id);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            T entity = this.getById(id);
            if (entity != null) {
                session.remove(entity);
                session.getTransaction().commit();
            }
        }
    }
}
