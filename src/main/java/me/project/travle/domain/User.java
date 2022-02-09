package me.project.travle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity //JPA가 관리하는 객체
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id //pk
    @Column(length = 10)  // varchar(10)
    private String id;

    @Column(nullable = true, length = 64) //not null, varcahr(64)
    private String password;

    @Column(nullable = false,length=13) //not null, varchar(13)
    private String phoneNumber;

    private LocalDateTime createdDate;
}
