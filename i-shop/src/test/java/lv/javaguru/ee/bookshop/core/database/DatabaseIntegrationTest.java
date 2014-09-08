package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import lv.javaguru.ee.bookshop.core.domain.Permission;
import lv.javaguru.ee.bookshop.core.domain.Role;
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
//        tableNames.add("addresses");
        tableNames.add("books");
        tableNames.add("categories");
        tableNames.add("orders");
        tableNames.add("orderDetails");
        tableNames.add("permissions");
        tableNames.add("roles");

        return tableNames;
    }

    // Permission
    protected Permission createDefaultPermission() {
        Permission permission = getDefaultPermission();
        return permission;
    }

    protected Permission getDefaultPermission() {
        Permission permissionAddCategories = new Permission("PERM_ADD_CATEGORIES");
        return permissionAddCategories;
    }

    protected void savePermission(Permission permission) {
        permissionDAO.create(permission);
    }

    // Permission list
    protected List<Permission> createDefaulPermissionsList() {

        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(new Permission("PERM_ADD_CATEGORIES"));
        permissions.add(new Permission("PERM_ADD_BOOKS"));
        permissions.add(new Permission("PERM_CREATE_ORDER"));
        return permissions;
    }

    // Roles
    protected Role createDefaultRole() {
        Role role = getDefaultRole();
        return role;
    }

    protected Role getDefaultRole() {
        Role roleCustomer = new Role("ROLE_CUSTOMER");
        roleCustomer.setPermissions(createDefaulPermissionsList());
        return roleCustomer;
    }

    protected void saveRole(Role role) {
        roleDAO.create(role);
    }

    // Roles list
    protected List<Role> createDefaulRolesList() {

        List<Role> roles = new ArrayList<Role>();
        roles.add(createDefaultRole());
        return roles;
    }

    // Address
    protected Address createDefaulAddress() {

        Address address = new Address();
        address.setCountry("Latvia");
        address.setCity("Riga");
        address.setStreet("Brivibas");
        return address;
    }

    // Account
    protected Account createDefaultAccount() {
        Account account = getDefaultAccount();
        return account;
    }

    protected Account getDefaultAccount() {
        Account johnDoe = new Account();
        johnDoe.setAddress(createDefaulAddress());
        johnDoe.setEmailAddress("bar@test.com");
        johnDoe.setFirstName("Vasja");
        johnDoe.setLastName("Pupkin");
        johnDoe.setUsername("user");
        johnDoe.setPassword("password");
        johnDoe.setRoles(createDefaulRolesList());

//        Account johnDoe =
//
//                new AccountBuilder() {
//                    {
//                        address("Antwerp", "2000", "Meir", "1", "A", "BE");
//                        email("bar@test.com");
//                        credentials("admin", "secret");
//                        name("Super", "User");
//                        roleWithPermissions(createDefaultRole(),
//                                createDefaultPermission()
//                        );
//                    }
//                }.build();

        return johnDoe;
    }

    protected void saveAccount(Account account) {
        accountDAO.create(account);
    }

}
