package lv.javaguru.ee.deliveryagency.core.database.locking;

import java.util.concurrent.Callable;

public abstract class Task<T extends TaskResult> implements Callable<T> {

	
}
