package com.example.demo;

import jdk.jfr.Frequency;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
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

    @GetMapping("/Friends/add")
    public @ResponseBody String addNewFriend (@RequestParam String name, @RequestParam String adress, @RequestParam String phonenumber){
        Friends f = new Friends();
        f.setName(name);
        f.setAdress(adress);
        f.setPhonenumber(phonenumber);
        friendRepository.save(f);
        return "Saved";
    }

    @GetMapping("Friends/remove")
    public @ResponseBody String removeFriend(@RequestParam Long id){
        friendRepository.deleteById(id);
        return "Friend deleted";
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
    private String phonenumber;

    public Friends() {
    }

    public Friends(String name, String adress, String phonenumber) {
        this.name = name;
        this.adress = adress;
        this.phonenumber = phonenumber;
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
        return phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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