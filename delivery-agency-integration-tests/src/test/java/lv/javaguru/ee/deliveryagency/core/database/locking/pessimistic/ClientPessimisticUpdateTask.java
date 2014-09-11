package lv.javaguru.ee.deliveryagency.core.database.locking.pessimistic;

import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Random;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Viktor on 06/09/2014.
 */
public class ClientPessimisticUpdateTask implements Callable<ClientPessimisticUpdateTaskResult> {

    static Logger log = Logger.getLogger(ClientPessimisticUpdateTask.class);

    private PlatformTransactionManager transactionManager;
    private ClientDAO clientDAO;
    private Long clientId;

    public ClientPessimisticUpdateTask(PlatformTransactionManager transactionManager,
                                      ClientDAO clientDAO,
                                      Long clientId) {
        this.transactionManager = transactionManager;
        this.clientDAO = clientDAO;
        this.clientId = clientId;
    }

    @Override
    public ClientPessimisticUpdateTaskResult call() throws Exception {
        try {
            log.info("Start pessimistic update");
            doInTransaction();
            log.info("CLIENT UNLOCKED");
            log.info("End pessimistic update");
        } catch (Throwable e) {
            return new ClientPessimisticUpdateTaskResult(false);
        }
        return new ClientPessimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Client client = clientDAO.getById(clientId, new LockOptions(LockMode.PESSIMISTIC_WRITE));
                checkNotNull(client);

                log.info("CLIENT LOCKED");

                Random rnd = new Random();
                String clientFirstName = "" + rnd.nextInt(10000000);
                client.setFirstName(clientFirstName);

                clientDAO.update(client);
            }
        });
    }

}
