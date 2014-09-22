package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.GetCategoryCommand;
import lv.javaguru.ee.bookshop.core.commands.GetCategoryResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class GetCategoryCommandHandler
        implements DomainCommandHandler<GetCategoryCommand, GetCategoryResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;


    @Override
    public GetCategoryResult execute(GetCategoryCommand command) {
        validateCommand(command);
        Category category = jpacrudOperationDAO.getById(Category.class, command.getCategoryId());

        if (category == null || !category.getCategoryId().equals(command.getCategoryId())) {
            throw new RuntimeException("Category id not valid");
        }

        return new GetCategoryResult(category);
    }

    private void validateCommand(GetCategoryCommand command) {
        checkNotNull(command, "GetCategoryCommand must not be null");
        checkNotNull(command.getCategoryId(), "Category id must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetCategoryCommand.class;
    }
}
