package com.example.service;

import com.example.dto.AuthDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    private final Logger log = LoggerFactory.getLogger(AuthService.class);

    public String registration(RegistrationDTO dto){
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            return "failed";
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);
        return "success";
    }
    public void login(AuthDTO dto){
        log.info("registration  " + dto.getEmail());
        emailSenderService.sendEmailVerification(dto.getEmail());
    }
}
