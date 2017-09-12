package de.scape.app.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 *
 */
@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public <T> void save(T entity) {
         getSession().saveOrUpdate(entity);
    }

    @Override
    public <T> T findById(final Serializable id, Class<T> entity) {
         return (T) getSession().get(entity.getName(), id);
    }

    @Override
    public <T> void update(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public List<?> findAllEntities(String className) {
        return getSession().createQuery("select c from " + className + " c ").getResultList();
    }

    @Override
    public <T> T findByName(String className,String name) {
        return (T) getSession().createQuery("select c from " + className + " c  where c.name like '"+name+"'").getSingleResult();
    }

    @Override
    public <T> void persist(T entity) {
        getSession().persist(entity);
    }

    @Override
    public <T> void merge(T entity) {
        getSession().merge(entity);
    }


}
