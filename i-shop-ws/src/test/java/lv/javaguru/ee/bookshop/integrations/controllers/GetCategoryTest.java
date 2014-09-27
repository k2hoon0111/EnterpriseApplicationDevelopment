package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testGetCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("C++");
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
        MatcherAssert.assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));

        Long categoryId = createdCategoryDTO.getCategoryId();

        CategoryDTO getCategoryDTO = RestFixture.getCategory(categoryId);
        MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
        MatcherAssert.assertThat(getCategoryDTO.getName(), is("C++"));

    }

    @Test
    public void testGetOrderWithWrongId() {
//        Long max = Long.MAX_VALUE;
//        try {
//            RestFixture.getCategory(max);
//        } catch (HttpClientErrorException e) {
//            TestCase.assertEquals("Entity " + Category.class.getName() + " not found by id " + max, e.getResponseBodyAsString());
//            Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
//        }

//        try {
//            RestFixture.getCategory(Long.MAX_VALUE);
//        } catch (RestException e) {
//            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
//        }
    }

}
