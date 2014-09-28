package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

import java.util.concurrent.Callable;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Random;

/**
 *
 * @author dell
 */
class ProductOptimisticUpdateTask implements Callable<ProductOptimisticUpdateTaskResult> {

    private final PlatformTransactionManager transactionManager;
    private final ProductDAO productDAO;
    private final Long id;

    ProductOptimisticUpdateTask(PlatformTransactionManager transactionManager, ProductDAO productDAO, Long id) {
        this.transactionManager = transactionManager;
        this.productDAO = productDAO;
        this.id = id;
    }

    @Override
    public ProductOptimisticUpdateTaskResult call() throws Exception {
        try {
            doInTransaction();
        } catch (Throwable e) {
            return new ProductOptimisticUpdateTaskResult(false);
        }
        return new ProductOptimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Product product = productDAO.getById(id);
                checkNotNull(product);

                Random rnd = new Random();  
                
                String title = "New_title_" + rnd.nextInt(10000000);
                product.setTitle(title);

                productDAO.update(product);
            }
        });
    }

}
