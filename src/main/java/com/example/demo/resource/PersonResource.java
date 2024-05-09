package com.example.demo.resource;

import java.util.List;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;

@RestController
public class PersonResource {

    Counter visitCounter;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public PersonResource(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter").description("Number of visits to the site").register(registry);
    }



    @GetMapping("/")
    public List<Person> getAll()  {
        visitCounter.increment();
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable int id)  {
        visitCounter.increment();
        return personRepository.findById(id).get();
    }

    @PostMapping("/")
    @Transactional
    public Person newPerson(@RequestBody Person person)  {
        return personRepository.save(person);
    }




}
