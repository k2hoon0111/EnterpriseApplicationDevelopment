package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Category;

/**
 * Created by MumboJumbo on 17/09/14.
 */
public class CreateCategoryResult implements DomainCommandResult {

    private Category category;

    public CreateCategoryResult(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}

