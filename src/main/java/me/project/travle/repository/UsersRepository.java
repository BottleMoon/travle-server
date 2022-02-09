package me.project.travle.repository;


import me.project.travle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.phoneNumber = ?1")
    public Optional<User> findByPhoneNumber(String number);
}
