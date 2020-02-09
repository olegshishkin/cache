package com.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CacheConfig(cacheNames = {"persons"})
@Slf4j
@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    public static final String FETCH_FROM_DB = "Fetch from DB %s";

    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Cacheable(key = "#name")
    @Override
    public Optional<Person> findByName(String name, UUID uuid) {
        log.info(String.format(FETCH_FROM_DB, uuid));
        return repository.findByName(name);
    }

    @Cacheable(key = "#root.method.name")
    @Override
    public List<Person> findAll(UUID uuid) {
        log.info(String.format(FETCH_FROM_DB, uuid));
        return repository.findAll();
    }

    @CachePut(key = "#person.name")
    @Override
    public Person save(Person person, UUID uuid) {
        log.info(String.format(FETCH_FROM_DB, uuid));
        return repository.save(person);
    }

    @CacheEvict(value = "persons", allEntries = true)
    @Override
    public void clear() {
        // no op
    }
}
