package lv.javaguru.ee.bookshop.core.database.jpa;

import lv.javaguru.ee.bookshop.config.CoreConfig;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = CoreConfig.class)
//@ActiveProfiles("test")
@TransactionConfiguration(transactionManager = "jpaTX", defaultRollback = false)
public abstract class DatabaseJPATest {

  @Autowired
  @Qualifier("jpaTX")
  protected PlatformTransactionManager transactionManager;

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private JPACRUDOperationDAO jpacrudOperationDAO;


  @Before
  public void cleanDatabase() {
    List<String> tableNames = getTableNames();
    for (final String tableName : tableNames) {
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

  // Book
  protected Book createDefaultBook() {
    Book book = getDefaultBook();
    return book;
  }

  protected Book getDefaultBook() {
    Category category = createDefaultCategory();
    jpacrudOperationDAO.create(category);

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
    jpacrudOperationDAO.create(book);
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


  protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
    TransactionTemplate tt = new TransactionTemplate(transactionManager);
    tt.execute(callbackWithoutResult);
  }

}
