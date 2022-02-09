package me.project.travle.dto.user;

import lombok.Getter;
import me.project.travle.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {
    String id;

    String password;

    String phoneNumber;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .phoneNumber(phoneNumber)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
