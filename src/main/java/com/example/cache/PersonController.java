package com.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/person")
    public Person find(@RequestParam("name") String name) {
        UUID uuid = UUID.randomUUID();
        log.info("find " + uuid);
        return service.findByName(name, uuid).orElse(null);
    }

    @GetMapping("/")
    public List<Person> getAll() {
        UUID uuid = UUID.randomUUID();
        log.info("all " + uuid);
        return service.findAll(uuid);
    }

    @GetMapping("/add")
    public Person add(@RequestParam("name") String name, @RequestParam("age") int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        UUID uuid = UUID.randomUUID();
        log.info("add " + uuid);
        return service.save(person, uuid);
    }

    @GetMapping("/clear")
    public Person clear() {
        log.info("Clear cache");
        return null;
    }
}
