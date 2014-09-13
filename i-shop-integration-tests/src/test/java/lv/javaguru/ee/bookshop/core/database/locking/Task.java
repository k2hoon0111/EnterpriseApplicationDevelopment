package lv.javaguru.ee.bookshop.core.database.locking;

import java.util.concurrent.Callable;

public abstract class Task<T extends TaskResult> implements Callable<T> {

	
}
