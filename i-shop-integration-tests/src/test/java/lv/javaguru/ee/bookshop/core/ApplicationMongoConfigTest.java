/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lv.javaguru.ee.bookshop.core;

import lv.javaguru.ee.bookshop.config.MongoConfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Integration test bootstrapping an {@link org.springframework.context.ApplicationContext} from both XML and JavaConfig to assure the general setup
 * is working.
 *
 * @author Oliver Gierke
 */
public class ApplicationMongoConfigTest {

	@Test
	public void bootstrapAppFromJavaConfig() {

		ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
		assertThat(context, is(notNullValue()));
	}
}
