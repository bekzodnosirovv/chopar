package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

//    @NotBlank(message = "Name required")
//    @NotNull(message = "name is null")
//    @Size(min = 3, message = "Name should be minimum 3")
    private String name;

//    @NotNull(message = "email is null!")
//    @Email(message = "Email required")
    private String email;
//    @NotBlank(message = "Password required")
//    @NotNull(message = "Password is null!")
//    @Size(min = 8, message = "password should be minimum 8!")
    private String phone;
    private String password;
    private LocalDateTime dateBirth;

//    private AttachDTO photo;
    private String photoUrl;

//    @NotBlank(message = "Role required")
    private ProfileRole role;
//    @NotBlank(message = "Status required")
    private ProfileStatus status;
    private LocalDateTime createdDate;
}
