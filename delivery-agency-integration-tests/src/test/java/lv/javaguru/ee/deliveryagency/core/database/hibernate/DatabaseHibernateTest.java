package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddressBuilder.*;
import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfoBuilder.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryAddressDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryInfoDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.ClientBuilder;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
//@ActiveProfiles("test")
@TransactionConfiguration(transactionManager = "hibernateTX", defaultRollback = false)
public abstract class DatabaseHibernateTest {

	@Autowired
	@Qualifier("hibernateTX")
	protected PlatformTransactionManager transactionManager;

	@Autowired
	protected SessionFactory sessionFactory;

    @Autowired
    protected ClientDAO clientDAO;

    @Autowired
    protected DeliveryAddressDAO deliveryAddressDAO;

    @Autowired
    protected DeliveryInfoDAO deliveryInfoDAO;

	@Autowired
	protected DeliveryDAO deliveryDAO;
	

    @Before
	public void cleanDatabase() {
		List<String> tableNames = getTableNames();
		for(final String tableName : tableNames) {
			doInTransaction(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
					Session session = sessionFactory.getCurrentSession();
					String queryString = "DELETE FROM " + tableName;
					Query query = session.createSQLQuery(queryString);
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
			
	
	protected Delivery getDefaultDelivery() {
		return new Delivery();
	}
	
	protected Delivery createDefaultDelivery() {
		Delivery delivery = getDefaultDelivery();
		deliveryDAO.create(delivery);
		return delivery;
	}

	protected Client getDefaultClient(Delivery delivery) {
		return ClientBuilder.createClient()
				.with(delivery)
				.withFirstName("Vasja")
				.withLastName("Pupkin")
				.withEmail("vasja.pupkin@gmail.com")
				.withPhone("12345")
				.withSpecialNotes("notes").build();
	}

	protected DeliveryAddress getDefaultDeliveryAddress(Delivery delivery) {
		return createDeliveryAddress()
				.with(delivery)
				.withCity("Riga")
				.withPostIndex("LV-10")
				.withStreet("Lomonosova")
				.withHouse("1")
				.withFlat("1").build();
	}

	protected DeliveryAddress createDefaultDeliveryAddress(Delivery delivery) {
		DeliveryAddress deliveryAddress = getDefaultDeliveryAddress(delivery);
		deliveryAddressDAO.create(deliveryAddress);
		return deliveryAddress;
	}

	protected DeliveryInfo getDefaultDeliveryInfo() {
		return createDeliveryInfo()
				.withDesiredDeliveryDate(new Date())
				.withDesiredDeliveryTimeFrom("10:00")
				.withDesiredDeliveryTimeTo("12:00")
				.withDeliveryNotes("kod 123").build();
	}

	protected DeliveryInfo createDefaultDeliveryInfo() {
		DeliveryInfo deliveryInfo = getDefaultDeliveryInfo();
		deliveryInfoDAO.create(deliveryInfo);
		return deliveryInfo;
	}
	
	protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
		TransactionTemplate tt = new TransactionTemplate(transactionManager);
		tt.execute(callbackWithoutResult);
	}

}
