package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Category;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteCategoryResult implements DomainCommandResult {

    private Category category;

    public DeleteCategoryResult(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}