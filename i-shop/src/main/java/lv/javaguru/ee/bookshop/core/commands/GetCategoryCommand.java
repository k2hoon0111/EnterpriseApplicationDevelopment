package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 17/09/14.
 */
public class GetCategoryCommand implements DomainCommand<GetCategoryResult> {

    private Long categoryId;

    public GetCategoryCommand(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

}