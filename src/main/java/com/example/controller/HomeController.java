package com.example.controller;

import com.example.config.CustomUserDetails;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/home")
    public String home(Model model) {
        if (!model.containsAttribute("auth")) {
            model.addAttribute("passwordSend", false);
            model.addAttribute("name", false);
            model.addAttribute("auth", new AuthDTO());
        }
        if (model.containsAttribute("enter")){
            CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO = profileService.toDto(customUserDetails.getProfile());
            model.addAttribute("profile", profileDTO);
        }
        return "index";
    }

    @PostMapping("/home")
    public String homePost(Model model){
        model.addAttribute("enter", true);
        return "redirect:/home";
    }

    @PostMapping("/errorPage")
    public String error(){
        return "errorPage";
    }


    @GetMapping({"", "/"})
    public String home() {
        return "redirect:/home";
    }
}
