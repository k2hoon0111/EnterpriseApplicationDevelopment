package lv.javaguru.ee.bookshop.core.domain;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class BookSearchCriteria {

    private String title;
    private Category category;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

}
