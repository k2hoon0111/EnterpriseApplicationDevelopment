package lv.javaguru.ee.warehouse.core.database;

import java.util.List;

/**
 * Created by Yuri D. on 2014.09.23..
 */
public interface CRUDOperationDAO<E, K> {

    void create(E entity);

    E getById(K key);

    E getRequired(K key);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();

}
