package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.database.CRUDOperationDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;

/**
 * Created by Viktor on 23/05/2014.
 */
abstract class CRUDOperationDAOImpl<E, K extends Serializable> implements CRUDOperationDAO<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class daoType;


    public CRUDOperationDAOImpl() {
        daoType = (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void create(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public E getById(K key) {
        return (E) getCurrentSession().get(daoType, key);
    }

    @Override
    public E getById(K key, LockOptions lockOptions) {
        return (E) getCurrentSession().get(daoType, key, lockOptions);
    }

    
    
    
    @Override
    public void update(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<E> getAll() {
        return getCurrentSession().createCriteria(daoType).list();
    }

    @Override
    public E merge(E entity) {
        return (E) getCurrentSession().merge(entity);
        
        
    }

    
    
}