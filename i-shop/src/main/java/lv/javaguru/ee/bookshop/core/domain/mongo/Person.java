package lv.javaguru.ee.bookshop.core.domain.mongo;

import org.springframework.data.annotation.Id;

/**
 * Created by nikoboro on 2014.10.20..
 */
public class Person {
  @Id
  private String id;
  private String firstname;
  private String lastname;
}
