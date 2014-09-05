package lv.javaguru.ee.bookshop.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
