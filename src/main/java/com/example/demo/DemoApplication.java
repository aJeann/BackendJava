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
    ApplicationRunner applicationRunner(GreetingRepository greetingRepository, FriendRepository friendRepository){
        return args -> {
            greetingRepository.save(new Greeting("Hello"));
            greetingRepository.save(new Greeting("Hi"));
            friendRepository.save(new Friends("Axel Jeansson", "Valsgärdevägen 10", "0707677207"));
            friendRepository.save(new Friends("Oscar Jeansson", "Drabantgatan 4C", "0707871773"));
        };
    }

}

@RestController
class HelloWorldController {
    private final GreetingRepository greetingRepository;
    private final FriendRepository friendRepository;

    public HelloWorldController(GreetingRepository greetingRepository, FriendRepository friendRepository) {
        this.greetingRepository = greetingRepository;
        this.friendRepository = friendRepository;
    }

    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

    @GetMapping("/greetings")
    Iterable<Greeting> greetings(){
        return greetingRepository.findAll();
    }

    @GetMapping("/Friends")
    Iterable<Friends> friends(){
        return friendRepository.findAll();
    }

}

@Entity
class Friends{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
    @Column
    private String adress;
    @Column
    private String phoneNmbr;

    public Friends() {
    }

    public Friends(String name, String adress, String phoneNmbr) {
        this.name = name;
        this.adress = adress;
        this.phoneNmbr = phoneNmbr;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoneNmbr() {
        return phoneNmbr;
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

interface FriendRepository extends CrudRepository<Friends, Long>{
}