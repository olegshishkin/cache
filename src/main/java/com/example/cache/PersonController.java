package com.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PersonController {

    public static final String FETCH_FROM_DB = "Fetch from DB";
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/person")
    public Person find(@RequestParam("name") String name) {
        log.info(FETCH_FROM_DB);
        return service.findByName(name).orElse(null);
    }

    @GetMapping("/")
    public List<Person> getAll() {
        log.info(FETCH_FROM_DB);
        return service.findAll();
    }

    @GetMapping("/add")
    public Person add(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info(FETCH_FROM_DB);
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return service.save(person);
    }

    @GetMapping("/clear")
    public Person clear() {
        return null;
    }
}
