package com.xandxr.fccspringboodemo.api;

import java.util.UUID;

import javax.validation.Valid;

import java.util.List;

import com.xandxr.fccspringboodemo.model.Person;
import com.xandxr.fccspringboodemo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                            .orElse(null);
    }

    @PostMapping
    public void addPerson(@Valid @RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @RequestBody Person person) {
        personService.updatePerson(id, person);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

}
