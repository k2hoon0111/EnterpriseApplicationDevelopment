package lv.javaguru.ee.bookshop.core.database.locking.pessimistic;

import lv.javaguru.ee.bookshop.core.database.locking.TaskResult;

/**
* Created by Viktor on 06/09/2014.
*/
public class BookPessimisticUpdateTaskResult implements TaskResult {

    private boolean updatedSuccessfully;

    public BookPessimisticUpdateTaskResult(boolean updatedSuccessfully) {
        this.updatedSuccessfully = updatedSuccessfully;
    }

    public boolean isUpdatedSuccessfully() {
        return updatedSuccessfully;
    }

}
