package com.xandxr.fccspringboodemo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xandxr.fccspringboodemo.model.Person;

import org.springframework.stereotype.Repository;

@Repository("fakeDao")
public class FakePersonDataAccess implements PersonDao {
    // acts as our fake database for now
    private static List<Person> DB = new ArrayList<Person>();


    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));

        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonbyId(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);

        if(!personMaybe.isPresent()) {
            return 0;
        }

        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person newPerson) {
        return selectPersonById(id)
               // set p to the new person if we find the the person, orElse return 0
               .map(foundPerson -> {
                   int indexOfPersonToUpdate = DB.indexOf(foundPerson);

                   if (indexOfPersonToUpdate >= 0) {
                    DB.set(indexOfPersonToUpdate, new Person(id, newPerson.getName()));
                    return 1;
                   }

                   return 0;
               })
               .orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                 .filter(person -> person.getId().equals(id))
                 .findFirst();
    }
}
