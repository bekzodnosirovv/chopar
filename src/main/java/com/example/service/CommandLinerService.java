package com.example.service;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommandLinerService implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public void run(String... args) throws Exception {
        if (!profileRepository.existsByEmail("mazgi@gmail.ru")){
            ProfileEntity profileEntity = new ProfileEntity();
            profileEntity.setName("Ali");
            profileEntity.setSurname("Aliyev");
            profileEntity.setRole(ProfileRole.ROLE_ADMIN);
            profileEntity.setVisible(true);
            profileEntity.setEmail("mazgi@gmail.ru");
            profileEntity.setStatus(ProfileStatus.ACTIVE);
            profileEntity.setCreatedDate(LocalDateTime.now());
            profileEntity.setPassword(MD5Util.encode("12345"));
            profileRepository.save(profileEntity);
        }
    }
}
