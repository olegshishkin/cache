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

@CacheConfig(cacheNames = {"persons"}, cacheManager = "caffeineCacheManager")
@Slf4j
@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Cacheable
    @Override
    public Optional<Person> findByName(String name) {
        return repository.findByName(name);
    }

    @Cacheable
    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @CachePut(key = "#person.name")
    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @CacheEvict(value = "persons", allEntries = true)
    @Override
    public void clear() {
        // no op
    }
}
