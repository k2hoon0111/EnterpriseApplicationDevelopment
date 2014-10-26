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
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductPropertiesAuditTest extends DatabaseHibernateTest {
	
        @Test 
	public void testProductPropertiesRevisions() {
		final AtomicLong entityId = new AtomicLong();
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                Product product = createDefaultProduct(); 
                                ProductProperties productProperties = createDefaultProductProperties(product);
				entityId.set(productProperties.getId());
			}
		});
		
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {                            
				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
				List<Number> revisions = reader.getRevisions(ProductProperties.class, entityId.get());
				assertThat(revisions.isEmpty(), is(false));
				assertThat(revisions.size(), is(1));
			}
		});
		
	}
        
	@Test        
	public void testGetProductPropertiesAtRevision() {
		final AtomicLong entityId = new AtomicLong();
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Product product = createDefaultProduct(); 
                                ProductProperties productProperties = createDefaultProductProperties(product);
				entityId.set(productProperties.getId());
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                ProductProperties pp = productPropertiesDAO.getById(entityId.get());                                
                                pp.setValue("ZZZ");
				productPropertiesDAO.update(pp);
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {				                                
                                ProductProperties pp = productPropertiesDAO.getById(entityId.get());                                
                                pp.setValue("SSS");
				productPropertiesDAO.update(pp);
			}
		});

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
				List<Number> revisions = reader.getRevisions(ProductProperties.class, entityId.get());
				assertThat(revisions.size(), is(3));
				
                                ProductProperties ppAtRevision = (ProductProperties) reader.createQuery()
						.forEntitiesAtRevision(ProductProperties.class, revisions.get(1))
						.add(AuditEntity.id().eq(entityId.get()))
						.getSingleResult();
                                                                
				assertThat(ppAtRevision.getValue(), is("ZZZ"));

				ppAtRevision = (ProductProperties) reader.createQuery()
						.forEntitiesAtRevision(ProductProperties.class, revisions.get(2))
						.add(AuditEntity.id().eq(entityId.get()))
						.getSingleResult();
				assertThat(ppAtRevision.getValue(), is("SSS"));
			}
		});

	}
}
