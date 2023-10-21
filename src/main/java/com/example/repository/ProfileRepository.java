package com.example.repository;

import com.example.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query("update ProfileEntity set password = :currentCode where email = :email")
    void updatePassword(@Param("email") String username, @Param("currentCode") String currentCode);
}
