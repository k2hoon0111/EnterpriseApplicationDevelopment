package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by nikoboro on 2014.09.19..
 */


/**
 * Created by nikoboro on 2014.09.18..
 */

public class UpdateCategoryCommand implements DomainCommand<UpdateCategoryResult> {

  private Long categoryId;
  private String name;

  public UpdateCategoryCommand(
      Long categoryId,
      String name) {
    this.categoryId = categoryId;
    this.name = name;

  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
