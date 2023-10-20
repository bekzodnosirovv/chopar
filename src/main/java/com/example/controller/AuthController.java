package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.service.AuthService;
import com.example.service.ProfileService;
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
    @Autowired
    public ProfileService profileService;

    @PostMapping("/goToLogin")
    public String goToLogin(Model model, String email){
        Boolean isPresent = profileService.findProfileByEmail(email);
        if (isPresent){
            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(email);
            authService.login(authDTO);
            model.addAttribute("profile", authDTO);
            model.addAttribute("failed", false);
            model.addAttribute("passwordSend", true);
            return "redirect:/home";
        }
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail(email);
        model.addAttribute("profile", registrationDTO);
        model.addAttribute("activeLink", "admins");
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("profile", new RegistrationDTO());
        model.addAttribute("activeLink", "admins");
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute RegistrationDTO dto, Model model) {
        String result = authService.registration(dto);
        model.addAttribute("activeLink", "admins");
        if (!result.equals("success")) {
            return "failed";
        }
        return "index";
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
