//package lv.javaguru.ee.bookshop.core.database.locking.optimistic;
//
//import lv.javaguru.ee.bookshop.core.database.ClientDAO;
//import lv.javaguru.ee.bookshop.core.domain.Client;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import java.util.Random;
//import java.util.concurrent.Callable;
//
//import static com.google.common.base.Preconditions.checkNotNull;
//
//public class ClientOptimisticUpdateTask implements Callable<ClientOptimisticUpdateTaskResult> {
//
//	private PlatformTransactionManager transactionManager;
//	private ClientDAO clientDAO;
//	private Long clientId;
//
//	public ClientOptimisticUpdateTask(PlatformTransactionManager transactionManager,
//                                      ClientDAO clientDAO,
//                                      Long clientId) {
//		this.transactionManager = transactionManager;
//		this.clientDAO = clientDAO;
//		this.clientId = clientId;
//	}
//
//	@Override
//	public ClientOptimisticUpdateTaskResult call() throws Exception {
//		try {
//			doInTransaction();
//		} catch (Throwable e) {
//			return new ClientOptimisticUpdateTaskResult(false);
//		}
//		return new ClientOptimisticUpdateTaskResult(true);
//	}
//
//	private void doInTransaction() {
//		TransactionTemplate tt = new TransactionTemplate(transactionManager);
//		tt.execute(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Client client = clientDAO.getById(clientId);
//				checkNotNull(client);
//
//				Random rnd = new Random();
//				String clientFirstName = "" + rnd.nextInt(10000000);
//				client.setFirstName(clientFirstName);
//
//				clientDAO.update(client);
//			}
//		});
//	}
//
//}
