package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Permission;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(permission.getPermissionId(), is(nullValue()));
        savePermission(permission);
        assertThat(permission.getPermissionId(), is(notNullValue()));
    }
}