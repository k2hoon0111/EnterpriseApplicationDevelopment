package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Role;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 06/09/14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class RoleDAOImplIntegrationTest extends DatabaseIntegrationTest {
    @Test
    public void testCreateClient() {
        Role role = createDefaultRole();
        assertThat(role.getRoleId(), is(nullValue()));
        saveRole(role);
        assertThat(role.getRoleId(), is(notNullValue()));
    }
}
