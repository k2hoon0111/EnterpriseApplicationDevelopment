package lv.javaguru.ee.deliveryagency.core.database.jpa;


public interface JPACRUDOperationDAO {

	<T> void create(T entity);
	
	<T, ID> T getById(Class<T> entityClass, ID id);

	<T> void update(T entity);

	<T> void delete(T entity);
	
}
