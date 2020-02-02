package com.example.cache;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    Optional<Person> findByName(String name, UUID uuid);

    List<Person> findAll(UUID uuid);

    Person save(Person person, UUID uuid);

    void clear();
}
