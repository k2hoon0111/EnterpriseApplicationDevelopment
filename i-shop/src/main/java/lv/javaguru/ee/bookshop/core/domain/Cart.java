package lv.javaguru.ee.bookshop.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public class Cart implements Serializable {

    private Map<Book, Integer> books = new HashMap<Book, Integer>();

    public Map<Book, Integer> getBooks() {
        return Collections.unmodifiableMap(this.books);
    }

    public void addBook(Book book) {
        if (this.books.containsKey(book)) {
            int quantity = this.books.get(book);
            quantity++;
            this.books.put(book, quantity);
        } else {
            this.books.put(book, 1);
        }
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public void clear() {
        this.books.clear();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("books", this.books.keySet());
        return builder.build();
    }

}
