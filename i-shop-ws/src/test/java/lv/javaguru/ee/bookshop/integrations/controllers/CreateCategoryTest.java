package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testCreateCategory() {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName("C++");

        ResponseEntity<CategoryDTO> createCategoryResponse
                = RestFixture.createCategory(categoryDTO);
        CategoryDTO createCategoryDTO = createCategoryResponse.getBody();

        assertThat(createCategoryDTO.getCategoryId(), is(notNullValue()));
    }

}
