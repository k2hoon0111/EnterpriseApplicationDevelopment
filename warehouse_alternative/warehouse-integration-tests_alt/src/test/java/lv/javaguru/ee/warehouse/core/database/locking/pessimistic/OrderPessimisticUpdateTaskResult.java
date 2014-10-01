package lv.javaguru.ee.warehouse.core.database.locking.pessimistic;

import lv.javaguru.ee.warehouse.core.database.locking.TaskResult;

/**
 *
 * @author dell
 */
class OrderPessimisticUpdateTaskResult implements TaskResult {
    
    private final boolean updatedSuccessfully;

    public OrderPessimisticUpdateTaskResult(boolean updatedSuccessfully) {
        this.updatedSuccessfully = updatedSuccessfully;
    }
    
    public boolean isUpdatedSuccessfully() {
        return updatedSuccessfully;
    }
    
}
