package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

/**
 *
 * @author dell
 */
class WarehouseOptimisticUpdateTaskResult {
     
    private final boolean updatedSuccessfully;

    public WarehouseOptimisticUpdateTaskResult(boolean updatedSuccessfully) {
        this.updatedSuccessfully = updatedSuccessfully;
    }
     
    public boolean isUpdatedSuccessfully() {
        return updatedSuccessfully;
    } 
     
}
