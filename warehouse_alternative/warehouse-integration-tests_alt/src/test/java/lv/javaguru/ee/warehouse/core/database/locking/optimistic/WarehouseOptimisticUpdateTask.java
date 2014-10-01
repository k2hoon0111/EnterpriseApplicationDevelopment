package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Random;
import java.util.concurrent.Callable;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author dell
 */
class WarehouseOptimisticUpdateTask implements Callable<WarehouseOptimisticUpdateTaskResult> {

    private final PlatformTransactionManager transactionManager;
    private final WarehouseDAO warehouseDAO;
    private final Long id;
    
    public WarehouseOptimisticUpdateTask(PlatformTransactionManager transactionManager, WarehouseDAO warehouseDAO, Long id) {
        this.transactionManager = transactionManager;
        this.warehouseDAO = warehouseDAO;
        this.id = id;
    }
    
    @Override
    public WarehouseOptimisticUpdateTaskResult call() throws Exception {
        try {
            doInTransaction();
        } catch (Throwable e) {
            return new WarehouseOptimisticUpdateTaskResult(false);
        }
        return new WarehouseOptimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Warehouse warehouse = warehouseDAO.getById(id);
                checkNotNull(warehouse);

                Random rnd = new Random();  
                
                String description = "New_description_" + rnd.nextInt(10000000);
                warehouse.setDescription(description);

                warehouseDAO.update(warehouse);
            }
        });
    }
    
}
