package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Axel Jeansson
 * Date: 2021-04-09
 * Time: 14:51
 * Project: BackendJava
 * Copyright: MIT
 */

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

}
