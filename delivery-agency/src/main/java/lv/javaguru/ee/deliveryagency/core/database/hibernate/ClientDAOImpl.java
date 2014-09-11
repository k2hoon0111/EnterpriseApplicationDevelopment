package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import org.hibernate.LockOptions;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 30/07/2014.
 */
@Component
public class ClientDAOImpl extends CRUDOperationDAOImpl<Client, Long> implements ClientDAO {

    public Client getById(Long clientId, LockOptions lockOptions) {
        return (Client) getCurrentSession().get(Client.class, clientId, lockOptions);
    }

}
