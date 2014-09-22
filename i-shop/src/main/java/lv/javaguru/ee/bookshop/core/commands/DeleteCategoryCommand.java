package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteCategoryCommand implements DomainCommand<DeleteCategoryResult> {
    private Long categoryId;

    public DeleteCategoryCommand(
            Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}