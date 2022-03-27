package com.xandxr.fccspringboodemo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xandxr.fccspringboodemo.model.Person;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    List<Person> selectAllPeople();

    int deletePersonbyId(UUID id);

    int updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);

    // default implementations
    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
}
