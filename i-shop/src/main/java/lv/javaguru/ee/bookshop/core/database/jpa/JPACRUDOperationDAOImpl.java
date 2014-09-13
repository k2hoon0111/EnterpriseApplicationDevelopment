package lv.javaguru.ee.bookshop.core.database.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JPACRUDOperationDAOImpl implements JPACRUDOperationDAO {

    @Autowired
    protected EntityManager entityManager;

    @Override
    public <T> void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public <T, ID> T getById(Class<T> entityClass, ID id) {
        T entity = entityManager.find(entityClass, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity " + entityClass.getName() + " not found by id " + id);
        }
        return entity;
    }

    @Override
    public <T> void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }

}
