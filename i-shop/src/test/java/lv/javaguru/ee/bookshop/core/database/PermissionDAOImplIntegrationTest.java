package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Permission;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 06/09/14
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public class PermissionDAOImplIntegrationTest extends DatabaseIntegrationTest {

    @Test
    public void testCreatePermission() {
        Permission permission = createDefaultPermission();
//        Delivery delivery = createDefaultDelivery();
//        Client client = getDefaultClient(delivery);
//        assertThat(client.getClientId(), is(nullValue()));
//        saveClient(client);
//        assertThat(client.getClientId(), is(notNullValue()));
    }
}