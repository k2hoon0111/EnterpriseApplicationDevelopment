package lv.javaguru.ee.bookshop.integrations.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by MumboJumbo on 17/09/14.
 */
@XmlRootElement(name = "categoryDTO")

public class CategoryDTO {
    private Long categoryId;
    private String name;

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
