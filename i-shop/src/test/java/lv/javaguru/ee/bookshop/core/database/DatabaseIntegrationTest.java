package lv.javaguru.ee.bookshop.core.database;

//import lv.javaguru.ee.deliveryagency.core.domain.*;

import lv.javaguru.ee.bookshop.core.domain.Address;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddressBuilder.*;
//import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddressBuilder.createDeliveryAddress;
//import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfoBuilder.*;
//import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfoBuilder.createDeliveryInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationTestApplicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public abstract class DatabaseIntegrationTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Before
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cleanDatabase() {
        Session session = sessionFactory.getCurrentSession();
        List<String> tableNames = getTableNames();
        for (String tableName : tableNames) {
            String queryString = "DELETE FROM " + tableName;
            Query query = session.createSQLQuery(queryString);
            query.executeUpdate();
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("accounts");
        tableNames.add("addresses");
        tableNames.add("books");
        tableNames.add("categories");
        tableNames.add("orders");
        tableNames.add("orderDetails");
        tableNames.add("permissions");
        tableNames.add("roles");

        return tableNames;
    }

    protected Address getDefaultAddress() {
        return new Address();
    }

    protected void saveAddress(Address address) {
        addressDAO.create(address);
    }

    protected Address createDefaultAddress() {
        Address address = getDefaultAddress();
        saveAddress(address);
        return address;
    }

/*
       protected Delivery getDefaultDelivery() {
		return new Delivery();
	}
	
	protected void saveDelivery(Delivery delivery) {
		deliveryDAO.create(delivery);
	}
	
	protected Delivery createDefaultDelivery() {
		Delivery delivery = getDefaultDelivery();
		saveDelivery(delivery);
		return delivery;
	}

	protected void saveClient(Client client) {
		clientDAO.create(client);
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

	protected void saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		deliveryAddressDAO.create(deliveryAddress);
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
		saveDeliveryAddress(deliveryAddress);
		return deliveryAddress;
	}

	protected void saveDeliveryInfo(DeliveryInfo deliveryInfo) {
		deliveryInfoDAO.create(deliveryInfo);
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
		saveDeliveryInfo(deliveryInfo);
		return deliveryInfo;
	}
*/
}
