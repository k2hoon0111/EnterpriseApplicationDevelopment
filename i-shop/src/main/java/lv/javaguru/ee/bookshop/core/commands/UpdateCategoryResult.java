package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Category;

/**
 * Created by nikoboro on 2014.09.19..
 */

public class UpdateCategoryResult implements DomainCommandResult {

  private Category category;

  public UpdateCategoryResult(Category category) {
    this.category = category;
  }

  public Category getCategory() {
    return category;
  }
}