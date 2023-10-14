package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "index";
    }

    @PostMapping("")
    public String loginSuccess() {

        return "index";
    }

    public String homePost() {
        return "redirect:/home";
    }
}
