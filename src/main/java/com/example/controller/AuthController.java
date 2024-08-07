package com.example.controller;

import com.example.dto.AuthDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public AuthService authService;
    @Autowired
    public ProfileService profileService;

    @PostMapping("/goToLogin")
    public String goToLogin(Model model, String email, RedirectAttributes redirectAttrs) {
        Boolean isPresent = profileService.findProfileByEmail(email);
        if (isPresent) {
            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(email);
            authService.login(authDTO);
            redirectAttrs.addFlashAttribute("auth", authDTO);
            redirectAttrs.addFlashAttribute("passwordSend", true);
            return "redirect:/home";
        }
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail(email);
        model.addAttribute("profile", registrationDTO);
        model.addAttribute("active", false);
        return "account";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("profile", new RegistrationDTO());
        model.addAttribute("activeLink", "admins");
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute RegistrationDTO dto, RedirectAttributes redirectAttrs) {
        String result = authService.registration(dto);
        if (!result.equals("success")) {
            return "errorPage";
        }
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(dto.getEmail());
        authService.login(authDTO);
        redirectAttrs.addFlashAttribute("auth", authDTO);
        redirectAttrs.addFlashAttribute("passwordSend", true);
        return "redirect:/home";
    }

    @GetMapping("/failed")
    public String getFailed(Model model) {
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
