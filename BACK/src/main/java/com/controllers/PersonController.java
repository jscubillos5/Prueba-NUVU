package com.controllers;

import com.dto.PersonDto;
import com.model.Person;
import com.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@RestController
@RequestMapping(path = "/back/Person/")
public class PersonController {

    @Autowired
    PersonService personService;

    @ApiOperation(value = "Permite identificar si el controlador de personas esta arriba")
    @RequestMapping("home")
    public String home() {
        return "Home Person";
    }

    @ApiOperation(value = "Retorna la lista de personas registradas en el sistema")
    @GetMapping(
            path = "get-persons",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<List<Person>> getPersons() {
        return personService.getPersons();
    }

    @ApiOperation(value = "Permite registrar una persona en el sistema")
    @PostMapping(
            path = "add-person",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<Person> addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @ApiOperation(value = "Permite eliminar una persona en el sistema")
    @PostMapping(
            path = "delete-person",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<Long> deletePerson(@Valid @RequestParam("id") Long id) {
        return personService.deletePerson(id);
    }
}
