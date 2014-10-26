package lv.javaguru.ee.bookshop.core.domain.mongo;

/**
 * Created by MumboJumbo on 26/10/14.
 */

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "categories")
public class Category extends AbstractDocument {

    @Field("name")
    String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}