package lv.javaguru.ee.deliveryagency.core;

import lv.javaguru.ee.deliveryagency.config.CoreConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
public class SpringContextTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Test
	public void appContextShouldBeNotNull() {
		assertThat(applicationContext, is(notNullValue()));
	}	
	
}
