package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @GetMapping("/goToRegistration")
    public String goToRegistration(Model model){
        model.addAttribute("profile", new ProfileDTO());
        model.addAttribute("activeLink", "admins");
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute ProfileDTO dto, Model model) {
        String result = authService.registration(dto);
        model.addAttribute("activeLink", "admins");
        if (!result.equals("success")) {
            return "failed";
        }
        return "index";
    }

    @GetMapping("/goToLogin")
    public String goToLogin(Model model) {
        model.addAttribute("profile", new AuthDTO());
        model.addAttribute("failed", false);
        return "login";
    }

    @GetMapping("/failed")
    public String getFailed(Model model){
        model.addAttribute("profile", new AuthDTO());
        model.addAttribute("failed", false);
        return "login";
    }

    @PostMapping("/success")
    public String getSuccess(Model model) {
        model.addAttribute("acticeLink", "dashboard");
        return "dashboard";
    }

    @GetMapping("/toIndex")
    public String getMainPage() {
        return "index";
    }
}
