package com.nuvu.service;

import com.nuvu.dao.PersonDao;
import com.nuvu.dto.PersonDto;
import com.nuvu.model.Person;
import com.nuvu.util.LogBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@Service
public class PersonService {

    private static final Integer ERRORTYPEMESSAGE = 2;
    private static final Integer ERRORTYPEINFO = 1;

    @Autowired
    PersonDao personDao;
    @Autowired
    LogBack logBack;

    public HttpEntity<List<Person>> getPersons() {
        HttpStatus httpStatus = HttpStatus.OK;
        List<Person> persons = new ArrayList<>();
        try {
            persons = personDao.getPersons();
            logBack.addLog(ERRORTYPEINFO, PersonService.class.getName(), "getPersons", MessageFormat.format("Successful people were listed: {0}", persons));
        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), "getPersons", MessageFormat.format("Attention an error occurred when listing the people, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), persons));
        }
        return new ResponseEntity<>(persons, httpStatus);
    }

    public HttpEntity<Person> addPerson(PersonDto personDto) {
        final String nameMethod = "addPerson";
        HttpStatus httpStatus = HttpStatus.OK;
        Person person = new Person();
        try {
            person.setId(personDto.getId());
            person.setIdentification(personDto.getIdentification());
            person.setName(personDto.getName());
            person.setEmail(personDto.getEmail());
            person.setDateRegister(new Date());
            if (personDao.getPersonByIdentification(person.getIdentification()) != null) {
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention already registered person, Data: {0}", person));
            } else {
                personDao.addPerson(person);
                logBack.addLog(ERRORTYPEINFO, PersonService.class.getName(), nameMethod, MessageFormat.format("The person was registered successfully: {0}", person));
            }
        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention an error occurred when registering a person, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), person));
        }
        return new ResponseEntity<>(person, httpStatus);
    }

    public HttpEntity<Long> deletePerson(Long id) {
        final String nameMethod = "deletePerson";
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (personDao.getPerson(id) != null) {
                personDao.deletePerson(id);
                logBack.addLog(ERRORTYPEINFO, PersonService.class.getName(), nameMethod, MessageFormat.format("The person was successfully removed: {0}", id));
            } else {
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention unregistered person, Data: {0}", id));
            }
        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention an error occurred when deleting the person, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), id));
        }
        return new ResponseEntity<>(id, httpStatus);
    }
}
