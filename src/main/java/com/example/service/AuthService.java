package com.example.service;

import com.example.dto.AuthDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    private Random random = new Random();

    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            return "failed";
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);
        return "success";
    }

    public Integer login(AuthDTO dto) {
        Integer currentCode = random.nextInt(1000, 10000);
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()) {
            ProfileEntity entity = new ProfileEntity();
            entity.setId(UUID.randomUUID());
            entity.setEmail(dto.getEmail());
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setRole(ProfileRole.ROLE_USER);
            entity.setPassword(MD5Util.encode(String.valueOf(currentCode)));
            profileRepository.save(entity);
        } else {
            profileRepository.updatePassword(dto.getEmail(), MD5Util.encode(String.valueOf(currentCode)));
        }
        emailSenderService.sendEmailVerification(dto.getEmail(), currentCode);
        return 1;
    }
}
