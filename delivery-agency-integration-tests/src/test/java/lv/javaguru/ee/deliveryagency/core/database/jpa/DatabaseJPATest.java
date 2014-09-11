package lv.javaguru.ee.deliveryagency.core.database.jpa;

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

import lv.javaguru.ee.deliveryagency.config.CoreConfig;


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
		tableNames.add("clients_aud");

		// real tables
		tableNames.add("deliveryItems");
		tableNames.add("clients");
		tableNames.add("deliveryAddresses");
		tableNames.add("deliveryInfos");
		tableNames.add("deliveries");

		return tableNames;
	}

	protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
		TransactionTemplate tt = new TransactionTemplate(transactionManager);
		tt.execute(callbackWithoutResult);
	}

}
