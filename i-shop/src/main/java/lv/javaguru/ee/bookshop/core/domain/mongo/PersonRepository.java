package lv.javaguru.ee.bookshop.core.domain.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nikoboro on 2014.10.20..
 */
@Component
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
  // additional custom finder methods go here
  List<Person> findByLastname(String lastname);

  Page<Person> findByFirstname(String firstname, Pageable pageable);
}

