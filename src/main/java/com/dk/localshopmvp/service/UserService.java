package com.dk.localshopmvp.service;

import com.dk.localshopmvp.dto.UserSignupForm;
import com.dk.localshopmvp.entity.User;
import com.dk.localshopmvp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName    : com.dk.localshopmvp.service.User
 * fileName       : UserService
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(UserSignupForm form) {
        User user = User.of(form.getEmail(), form.getPassword(), form.getName(), form.getPhone());
        userRepository.save(user);
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
