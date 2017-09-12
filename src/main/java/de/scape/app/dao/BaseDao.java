package de.scape.app.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public interface BaseDao {

    <T> void save(T entity);

    <T> T findById(Serializable id,Class<T> entity);

    <T> void update(T entity);

    List<?> findAllEntities(String className);

    <T> T findByName(String className,String name);
    
     <T> void persist(T entity);
     
     <T> void merge(T entity);
     
     Session getSession();
    
}
