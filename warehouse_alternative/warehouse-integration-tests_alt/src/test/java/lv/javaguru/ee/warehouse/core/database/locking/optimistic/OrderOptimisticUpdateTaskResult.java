package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

/**
 *
 * @author dell
 */
class OrderOptimisticUpdateTaskResult {
    
    private final boolean updatedSuccessfully;

    public OrderOptimisticUpdateTaskResult(boolean updatedSuccessfully) {
        this.updatedSuccessfully = updatedSuccessfully;
    }
         
    public boolean isUpdatedSuccessfully() {
        return updatedSuccessfully;
    } 
    
}
