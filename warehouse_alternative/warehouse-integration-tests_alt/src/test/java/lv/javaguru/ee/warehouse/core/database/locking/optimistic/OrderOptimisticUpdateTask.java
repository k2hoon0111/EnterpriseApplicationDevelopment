package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

import java.util.Random;
import java.util.concurrent.Callable;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.domain.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author dell
 */
class OrderOptimisticUpdateTask implements Callable<OrderOptimisticUpdateTaskResult> {

    private final PlatformTransactionManager transactionManager;
    private final OrderDAO orderDAO;
    private final Long id;

    public OrderOptimisticUpdateTask(PlatformTransactionManager transactionManager, OrderDAO orderDAO, Long id) {
        this.transactionManager = transactionManager;
        this.orderDAO = orderDAO;
        this.id = id;
    }
    
    @Override
    public OrderOptimisticUpdateTaskResult call() throws Exception {
        try {
            doInTransaction();
        } catch (Throwable e) {
            return new OrderOptimisticUpdateTaskResult(false);
        }
        return new OrderOptimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Order order = orderDAO.getById(id);
                checkNotNull(order);

                Random rnd = new Random();  
                                 
                order.setAmount(rnd.nextInt(10000000));

                orderDAO.update(order);
            }
        });
    }
    
}
