package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

import lv.javaguru.ee.warehouse.core.database.locking.TaskResult;

/**
 *
 * @author dell
 */
class ProductOptimisticUpdateTaskResult implements TaskResult {

    private final boolean updatedSuccessfully;

    ProductOptimisticUpdateTaskResult(boolean updatedSuccessfully) {
        this.updatedSuccessfully = updatedSuccessfully;
    }

    public boolean isUpdatedSuccessfully() {
        return updatedSuccessfully;
    }

}
