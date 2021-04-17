package com.repositories;

import com.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByIdentification(String identification);
}
