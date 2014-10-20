package lv.javaguru.ee.bookshop.core.database.mongo;

import lv.javaguru.ee.bookshop.core.domain.mongo.Person;
import lv.javaguru.ee.bookshop.core.domain.mongo.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nikoboro on 2014.10.20..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PersonRepositoryTests {

  @Autowired
  PersonRepository repository;

  @Test
  public void readsFirstPageCorrectly() {

    Page<Person> persons = repository.findAll(new PageRequest(0, 10));
    assertThat(persons.isFirstPage(), is(true));
  }
}
