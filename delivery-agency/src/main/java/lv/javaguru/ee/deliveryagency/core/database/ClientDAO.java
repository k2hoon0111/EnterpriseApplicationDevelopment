package lv.javaguru.ee.deliveryagency.core.database;

import lv.javaguru.ee.deliveryagency.core.domain.Client;
import org.hibernate.LockOptions;

/**
 * Created by Viktor on 30/07/2014.
 */
public interface ClientDAO extends CRUDOperationDAO<Client, Long> {

    Client getById(Long clientId, LockOptions lockOptions);

}
