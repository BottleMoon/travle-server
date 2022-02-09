package me.project.travle.dto.user;

import lombok.Getter;
import me.project.travle.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    String id;

    String password;

    String phoneNumber;

    LocalDateTime createdDate;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.createdDate = user.getCreatedDate();
    }

}
