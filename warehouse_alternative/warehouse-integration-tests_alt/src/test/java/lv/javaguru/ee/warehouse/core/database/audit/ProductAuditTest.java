package lv.javaguru.ee.warehouse.core.database.audit;

import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.domain.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductAuditTest extends DatabaseHibernateTest {

	@Test        
	public void testProductRevisions() {
		final AtomicLong entityId = new AtomicLong();
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                Product product = createDefaultProduct();                                				
				entityId.set(product.getId());
			}
		});
		
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {                            
				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
				List<Number> revisions = reader.getRevisions(Product.class, entityId.get());
				assertThat(revisions.isEmpty(), is(false));
				assertThat(revisions.size(), is(1));
			}
		});
		
	}

	@Test         
        public void testGetProductAtRevision() {
		final AtomicLong entityId = new AtomicLong();
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Product product = createDefaultProduct();                                				
				entityId.set(product.getId());
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                Product product = productDAO.getById(entityId.get());
                                product.setTitle("AAA");				
				productDAO.update(product);
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Product product = productDAO.getById(entityId.get());
                                product.setTitle("BBB");				
				productDAO.update(product);
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
				List<Number> revisions = reader.getRevisions(Product.class, entityId.get());
				assertThat(revisions.size(), is(3));
				
				Product productAtRevision = (Product) reader.createQuery()
						.forEntitiesAtRevision(Product.class, revisions.get(1))
						.add(AuditEntity.id().eq(entityId.get()))
						.getSingleResult();
				assertThat(productAtRevision.getTitle(), is("AAA"));

				productAtRevision = (Product) reader.createQuery()
						.forEntitiesAtRevision(Product.class, revisions.get(2))
						.add(AuditEntity.id().eq(entityId.get()))
						.getSingleResult();
				assertThat(productAtRevision.getTitle(), is("BBB"));
			}
		});

	}
              
}
