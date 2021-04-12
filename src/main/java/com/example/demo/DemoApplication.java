package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(GreetingRepository greetingRepository){
        return args -> {
            greetingRepository.save(new Greeting("Hello"));
            greetingRepository.save(new Greeting("Hi"));
        };
    }

}

@RestController
class HelloWorldController {
    private final GreetingRepository greetingRepository;

    public HelloWorldController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

    @GetMapping("/greetings")
    Iterable<Greeting> greetings(){
        return greetingRepository.findAll();
    }

}

@Entity
class Greeting{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    public Greeting(){

    }

    public Greeting(String message){
        this.message = message;
    }

    public long getID(){
        return id;
    }

    public String getMessage(){
        return message;
    }

}

interface GreetingRepository extends CrudRepository<Greeting, Long>{

}
