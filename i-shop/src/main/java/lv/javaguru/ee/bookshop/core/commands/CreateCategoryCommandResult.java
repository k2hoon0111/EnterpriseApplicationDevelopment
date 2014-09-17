package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Category;

/**
 * Created by MumboJumbo on 17/09/14.
 */
public class CreateCategoryCommandResult implements DomainCommandResult {

    private Category category;

    public CreateCategoryCommandResult(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}

