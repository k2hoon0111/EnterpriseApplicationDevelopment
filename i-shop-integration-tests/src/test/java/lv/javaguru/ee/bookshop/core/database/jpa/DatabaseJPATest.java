package lv.javaguru.ee.bookshop.core.database.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import lv.javaguru.ee.bookshop.config.CoreConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
@ActiveProfiles("test")
@TransactionConfiguration(transactionManager = "jpaTX", defaultRollback = false)
public abstract class DatabaseJPATest {

	@Autowired
	@Qualifier("jpaTX")
	protected PlatformTransactionManager transactionManager;

	@Autowired
	private EntityManager entityManager;


	@Before
	public void cleanDatabase() {
		List<String> tableNames = getTableNames();
		for(final String tableName : tableNames) {
			doInTransaction(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
					String queryString = "DELETE FROM " + tableName;
					Query query = entityManager.createNativeQuery(queryString);
					query.executeUpdate();
				}
			});
		}
	}

	private List<String> getTableNames() {
		List<String> tableNames = new ArrayList<>();

        // audit tables
        tableNames.add("booksAudit");

        // real tables
        tableNames.add("accounts");
        tableNames.add("books");
        tableNames.add("categories");
        tableNames.add("orders");
        tableNames.add("orderDetails");
        tableNames.add("permissions");
        tableNames.add("roles");
        tableNames.add("roles_permissions");

		return tableNames;
	}

	protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
		TransactionTemplate tt = new TransactionTemplate(transactionManager);
		tt.execute(callbackWithoutResult);
	}

}