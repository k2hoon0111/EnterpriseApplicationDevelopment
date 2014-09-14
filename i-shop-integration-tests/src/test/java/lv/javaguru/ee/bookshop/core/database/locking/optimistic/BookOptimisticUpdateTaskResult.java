package lv.javaguru.ee.bookshop.core.database.locking.optimistic;

import lv.javaguru.ee.bookshop.core.database.locking.TaskResult;

public class BookOptimisticUpdateTaskResult implements TaskResult {

	private boolean updatedSuccessfully;

	public BookOptimisticUpdateTaskResult(boolean updatedSuccessfully) {
		this.updatedSuccessfully = updatedSuccessfully;
	}

	public boolean isUpdatedSuccessfully() {
		return updatedSuccessfully;
	}
}
