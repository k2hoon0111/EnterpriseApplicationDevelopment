//package lv.javaguru.ee.deliveryagency.core.database.audit;
//
//import lv.javaguru.ee.deliveryagency.core.database.hibernate.DatabaseHibernateTest;
//import lv.javaguru.ee.deliveryagency.core.domain.Client;
//import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
//import org.hibernate.envers.AuditReader;
//import org.hibernate.envers.AuditReaderFactory;
//import org.hibernate.envers.query.AuditEntity;
//import org.junit.Test;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ClientAuditTest extends DatabaseHibernateTest {
//
//	@Test
//	public void testClientRevisions() {
//		final AtomicLong clientId = new AtomicLong();
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = createDefaultDelivery();
//				Client client = getDefaultClient(delivery);
//				clientDAO.create(client);
//				clientId.set(client.getClientId());
//			}
//		});
//
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
//				List<Number> revisions = reader.getRevisions(Client.class, clientId.get());
//				assertThat(revisions.isEmpty(), is(false));
//				assertThat(revisions.size(), is(1));
//			}
//		});
//
//	}
//
//	@Test
//	public void testGetClientAtRevision() {
//		final AtomicLong clientId = new AtomicLong();
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = createDefaultDelivery();
//				Client client = getDefaultClient(delivery);
//				clientDAO.create(client);
//				clientId.set(client.getClientId());
//			}
//		});
//
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Client client = clientDAO.getById(clientId.get());
//				client.setFirstName("AAA");
//				clientDAO.update(client);
//			}
//		});
//
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Client client = clientDAO.getById(clientId.get());
//				client.setFirstName("BBB");
//				clientDAO.update(client);
//			}
//		});
//
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
//				List<Number> revisions = reader.getRevisions(Client.class, clientId.get());
//				assertThat(revisions.size(), is(3));
//
//				Client clientAtRevision = (Client) reader.createQuery()
//						.forEntitiesAtRevision(Client.class, revisions.get(1))
//						.add(AuditEntity.id().eq(clientId.get()))
//						.getSingleResult();
//				assertThat(clientAtRevision.getFirstName(), is("AAA"));
//
//				clientAtRevision = (Client) reader.createQuery()
//						.forEntitiesAtRevision(Client.class, revisions.get(2))
//						.add(AuditEntity.id().eq(clientId.get()))
//						.getSingleResult();
//				assertThat(clientAtRevision.getFirstName(), is("BBB"));
//			}
//		});
//
//	}
//
//}
