package lv.javaguru.ee.deliveryagency.core;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationTestApplicationContext.xml")
public class SpringContextIntegrationTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Test
	public void appContextShouldBeNotNull() {
		assertThat(applicationContext, is(notNullValue()));
	}	
	
}
