package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileDTO> findAll() {
        List<ProfileEntity> all = profileRepository.findAll();
        List<ProfileDTO> dtoList = new ArrayList<>();

        all.forEach(profile -> dtoList.add(toDto(profile)));
        return dtoList;
    }

    public String delete(Integer id) {
        profileRepository.deleteById(id);
        return "success";
    }

    public ProfileDTO findProfileById(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        ProfileDTO profileDTO = toDto(optional.get());
        return profileDTO;
    }

    public String update(ProfileDTO dto){
        Optional<ProfileEntity> optional = profileRepository.findById(dto.getId());
        if (optional.isEmpty()){
            return "failed";
        }
        ProfileEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return "success";
    }

    public ProfileDTO toDto(ProfileEntity profileEntity){
        ProfileDTO profileDTO=new ProfileDTO();
        profileDTO.setId(profileEntity.getId());
        profileDTO.setName(profileEntity.getName());
        profileDTO.setSurname(profileEntity.getSurname());
        profileDTO.setEmail(profileEntity.getEmail());
        profileDTO.setCreatedDate(profileEntity.getCreatedDate());
        return profileDTO;
    }

}
