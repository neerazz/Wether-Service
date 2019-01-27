package com.neeraj.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class MVCController {

    @GetMapping({"/","/home"})
    public String homePage(){
        return "home-page";
    }
}
