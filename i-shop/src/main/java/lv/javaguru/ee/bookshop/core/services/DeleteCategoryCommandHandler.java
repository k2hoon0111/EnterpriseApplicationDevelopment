package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DeleteCategoryCommand;
import lv.javaguru.ee.bookshop.core.commands.DeleteCategoryResult;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 19/09/14.
 */

@Component
public class DeleteCategoryCommandHandler
        implements DomainCommandHandler<DeleteCategoryCommand, DeleteCategoryResult> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public DeleteCategoryResult execute(DeleteCategoryCommand command) {
        validateCommand(command);
        Category category = selectCategoryEntityFromDB(command);
        categoryDAO.delete(category);

        return new DeleteCategoryResult(category);
    }

    private Category selectCategoryEntityFromDB(DeleteCategoryCommand command) {
        Category category = categoryDAO.getById(command.getCategoryId());
        return category;
    }

    private void validateCommand(DeleteCategoryCommand command) {
        checkNotNull(command, "UpdateCategoryCommand must not be null");
        checkNotNull(command.getCategoryId(), "Category id  must not be null");
    }

    @Override
    public Class getCommandType() {
        return DeleteCategoryCommand.class;
    }

}
