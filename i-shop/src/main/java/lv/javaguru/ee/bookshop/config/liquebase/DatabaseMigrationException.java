package lv.javaguru.ee.bookshop.config.liquebase;

public class DatabaseMigrationException extends RuntimeException {

	public DatabaseMigrationException(String message, Throwable cause) {
		super(message, cause);
	}
}
