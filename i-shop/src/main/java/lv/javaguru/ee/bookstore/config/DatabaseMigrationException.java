package lv.javaguru.ee.bookstore.config;

public class DatabaseMigrationException extends RuntimeException {

	public DatabaseMigrationException(String message, Throwable cause) {
		super(message, cause);
	}
}
