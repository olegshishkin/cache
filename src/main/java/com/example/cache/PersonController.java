package com.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/person")
    public Person find(@RequestParam("name") String name) {
        return service.findByName(name).orElse(null);
    }

    @GetMapping("/")
    public List<Person> getAll() {
        return service.findAll();
    }

    @GetMapping("/add")
    public Person add(@RequestParam("name") String name, @RequestParam("age") int age) {
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
