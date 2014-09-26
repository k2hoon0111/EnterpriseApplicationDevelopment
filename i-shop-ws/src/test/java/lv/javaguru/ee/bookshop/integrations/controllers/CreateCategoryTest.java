package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testCreateCategory() {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName("C++");

        ResponseEntity<CategoryDTO> createCategoryResponse
                = RestFixture.createCategory(categoryDTO);
        CategoryDTO createCategoryDTO = createCategoryResponse.getBody();

        MatcherAssert.assertThat(createCategoryDTO.getCategoryId(), is(notNullValue()));
    }

}
