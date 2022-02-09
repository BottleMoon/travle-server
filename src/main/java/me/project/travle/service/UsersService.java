package me.project.travle.service;

import me.project.travle.domain.User;
import me.project.travle.dto.user.UserRequestDto;
import me.project.travle.dto.user.UserResponseDto;
import me.project.travle.error.ApiException;
import me.project.travle.error.ApiExceptionAdvice;
import me.project.travle.error.ExceptionEnum;
import me.project.travle.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApiExceptionAdvice apiExceptionAdvice;

    UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, ApiExceptionAdvice apiExceptionAdvice) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.apiExceptionAdvice = apiExceptionAdvice;
    }

    public ResponseEntity createUser(UserRequestDto userRequestDto) {

        if (usersRepository.findById(userRequestDto.getId()).isPresent()) {
            //아이디 중복
            return apiExceptionAdvice.exceptionHandler(null, new ApiException(ExceptionEnum.ID_CONFLICT_EXCEPTION));
        } else if (usersRepository.findByPhoneNumber(userRequestDto.getPhoneNumber()).isPresent()) {
            //폰 번호 중복
            return apiExceptionAdvice.exceptionHandler(null, new ApiException(ExceptionEnum.PHONE_CONFLICT_EXCEPTION));
        } else {
            userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            User user = userRequestDto.toEntity();
            usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

    public UserResponseDto findUserById(String id) {
        Optional<User> user = usersRepository.findById(id);
        return user.map(UserResponseDto::new).orElseGet(null/*에러처리*/);
    }

    public ResponseEntity login(UserRequestDto userRequestDto) {
        Optional<User> user = usersRepository.findById(userRequestDto.getId());
        if (user.isPresent()) {
            if (passwordEncoder.matches(userRequestDto.getPassword(), user.get().getPassword())) {
                return null;
                //성공 응답
            }
        }
        return apiExceptionAdvice.exceptionHandler(null, new ApiException(ExceptionEnum.ID_OR_PASSWORD_EXCEPTION));
    }
}
