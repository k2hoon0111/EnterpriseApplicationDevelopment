package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateCategoryCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateCategoryResult;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 17/09/14.
 */

@Component
public class CreateCategoryCommandHandler
        implements DomainCommandHandler<CreateCategoryCommand, CreateCategoryResult> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public CreateCategoryResult execute(CreateCategoryCommand command) {
        validateCommand(command);

        Category category = createCategoryEntityFromCommand(command);
        categoryDAO.create(category);

        return new CreateCategoryResult(category);
    }

    private Category createCategoryEntityFromCommand(CreateCategoryCommand command) {
        Category category = new Category();
        category.setName(command.getName());
        return category;
    }

    private void validateCommand(CreateCategoryCommand command) {
        checkNotNull(command, "CreateCategoryCommand must not be null");
        checkNotNull(command.getName(), "Name must not be null");
    }

    @Override
    public Class getCommandType() {
        return CreateCategoryCommand.class;
    }

}
