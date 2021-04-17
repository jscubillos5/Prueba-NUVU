package com.dao;

import com.model.Person;
import com.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@Component
public class PersonDao {

    @Autowired
    PersonRepository personRepository;

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person getPersonByIdentification(String identification) {
        return personRepository.findByIdentification(identification);
    }

    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
