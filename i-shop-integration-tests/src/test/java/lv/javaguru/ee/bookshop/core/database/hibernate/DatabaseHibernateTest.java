package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.config.CoreConfig;
import lv.javaguru.ee.bookshop.core.database.*;
import lv.javaguru.ee.bookshop.core.domain.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class })
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
    private AccountDAO accountDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    protected BookDAO bookDAO;

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
    public void cleanDatabase() {
        List<String> tableNames = getTableNames();
        for (final String tableName : tableNames) {
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
        tableNames.add("books_AUD");

        // domen tables
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

        return johnDoe;
    }

    protected void saveAccount(Account account) {
        accountDAO.create(account);
    }

    // Book
    protected Book createDefaultBook() {
        Book book = getDefaultBook();
        return book;
    }

    protected Book getDefaultBook() {
        Category category = createDefaultCategory();
        categoryDAO.create(category);

        Book effectiveJava = new Book();
        effectiveJava.setAuthor("Joshua Bloch");
        effectiveJava.setCategory(category);
        effectiveJava.setDescription("Brings together seventy-eight indispensable programmer's rules of thumb.");
        effectiveJava.setIsbn("9780321356680");
        effectiveJava.setPrice(new BigDecimal("20.20"));
        effectiveJava.setTitle("Effective Java");
        effectiveJava.setYear(2002);
        return effectiveJava;

    }

    protected void saveBoook(Book book) {
        bookDAO.create(book);
    }

    // Category
    protected Category createDefaultCategory() {
        Category category = getDefaultCategory();
        return category;
    }

    protected Category getDefaultCategory() {
        Category category = new Category();
        category.setName("Java");
        return category;
    }

    protected void saveCategory(Category category) {
        categoryDAO.create(category);
    }

    // Order
    protected Order createDefaultOrder() {
        Order order = getDefaultOrder();
        return order;
    }

    protected Order getDefaultOrder() {
        Address address = createDefaulAddress();
        Account account = getDefaultAccount();
        saveAccount(account);

        Order order = new Order();
        order.setAccount(account);
        order.setBillingAddress(address);
        order.setShippingAddress(address);
        order.setDeliveryDate(new Date());
        order.setOrderDate(new Date());
        order.setBillingSameAsShipping(true);
        return order;
    }

    protected void saveOrder(Order order) {
        orderDAO.create(order);
    }

    // OrderDetail
    protected OrderDetail createDefaultOrderDetail() {
        OrderDetail orderDetail = getDefaultOrderDetail();
        return orderDetail;
    }

    protected OrderDetail getDefaultOrderDetail() {
        Book book = getDefaultBook();
        saveBoook(book);
        Order order = getDefaultOrder();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBook(book);
        orderDetail.setQuantity(3);
        return orderDetail;
    }

    protected void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailDAO.create(orderDetail);
    }


    protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(callbackWithoutResult);
    }

}
