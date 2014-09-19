package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.UpdateCategoryCommand;
import lv.javaguru.ee.bookshop.core.commands.UpdateCategoryResult;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nikoboro on 2014.09.19..
 */

@Component
public class UpdateCategoryCommandHandler
    implements DomainCommandHandler<UpdateCategoryCommand, UpdateCategoryResult> {

  @Autowired
  private CategoryDAO categoryDAO;

  @Override
  public UpdateCategoryResult execute(UpdateCategoryCommand command) {
    validateCommand(command);

    Category category = selectCategoryEntityFromDB(command);
    categoryDAO.update(category);

    return new UpdateCategoryResult(category);
  }

  private Category selectCategoryEntityFromDB(UpdateCategoryCommand command) {
    Category category = categoryDAO.getById(command.getCategoryId());
    category.setName(command.getName());
    return category;
  }

  private void validateCommand(UpdateCategoryCommand command) {
    checkNotNull(command, "UpdateCategoryCommand must not be null");
    checkNotNull(command.getCategoryId(), "Category id  must not be null");
    checkNotNull(command.getName(), "Name must not be null");
  }

  @Override
  public Class getCommandType() {
    return UpdateCategoryCommand.class;
  }

}
