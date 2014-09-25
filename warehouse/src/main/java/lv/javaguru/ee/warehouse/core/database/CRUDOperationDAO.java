package lv.javaguru.ee.warehouse.core.database;

import java.util.List;

/**
 * Created by Viktor on 23/05/2014.
 */
public interface CRUDOperationDAO<E, K> {

    void create(E entity);

    E getById(K key);

    void update(E entity);

    void delete(E entity);

    E merge(E entity);
    
    List<E> getAll();

}