package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping("")
    public String dashboard(){
        return "dashboard";
    }
    @PostMapping("")
    public String dashboardPost() {
        return "redirect:/dashboard";
    }
}
