package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    private Integer updateId;

    @GetMapping("")
    public String getProfileList(Model model) {
        List<ProfileDTO> all = profileService.findAll();
        model.addAttribute("profile", all);
        model.addAttribute("authObj", new ProfileDTO());
//        model.addAttribute("activeLink", "admins");
        return "dashboard";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        profileService.delete(id);
        return "dashboard";
    }

    @GetMapping("/goToUpdate/{id}")
    public String goToUpdate(Model model, @PathVariable("id") Integer id) {
        updateId = id;
        ProfileDTO profileDTO = profileService.findProfileById(id);
        model.addAttribute("profile", profileDTO);
//        model.addAttribute("isUpdate", true);
//        model.addAttribute("activeLink", "admins");
        return "profileUpdate";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("journal") ProfileDTO dto) {
        dto.setId(updateId);
        profileService.update(dto);
        return "redirect:/profile";
    }
}
