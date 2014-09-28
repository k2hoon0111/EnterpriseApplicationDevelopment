package lv.javaguru.ee.warehouse.core.database.locking;

import java.util.concurrent.Callable;

public abstract class Task<T extends TaskResult> implements Callable<T> {

	
}
