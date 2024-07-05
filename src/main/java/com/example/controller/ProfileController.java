package com.example.controller;

import com.example.config.CustomUserDetails;
import com.example.dto.OrdersDTO;
import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/account")
    public String account(Model model) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = profileService.toDto(customUserDetails.getProfile());
        model.addAttribute("profile", profileDTO);
        model.addAttribute("active", true);
        return "account";
    }

    @PostMapping("/account")
    public String accountPost(RedirectAttributes redirectAttrs) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = profileService.toDto(customUserDetails.getProfile());
        redirectAttrs.addFlashAttribute("profile", profileDTO);
        redirectAttrs.addFlashAttribute("active", true);
        return "redirect:/profile/account";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute ProfileDTO dto, Model model) {
        profileService.update(dto);
        model.addAttribute("profile", dto);
        model.addAttribute("active", true);
        return "account";
    }

    @GetMapping("/orders")
    public String orders(Model model){
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = profileService.toDto(customUserDetails.getProfile());
        List<OrdersDTO> orderDTOList = profileService.orders(profileDTO.getId());
        model.addAttribute("profile", profileDTO);
        model.addAttribute("active", true);
        model.addAttribute("orderList", orderDTOList);
        return "orders";
    }
}
