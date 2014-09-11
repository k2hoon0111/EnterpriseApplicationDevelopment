package lv.javaguru.ee.deliveryagency.core.database.locking.optimistic;

import lv.javaguru.ee.deliveryagency.core.database.locking.TaskResult;

public class ClientOptimisticUpdateTaskResult implements TaskResult {
	
	private boolean updatedSuccessfully;

	public ClientOptimisticUpdateTaskResult(boolean updatedSuccessfully) {
		this.updatedSuccessfully = updatedSuccessfully;
	}

	public boolean isUpdatedSuccessfully() {
		return updatedSuccessfully;
	}
}
