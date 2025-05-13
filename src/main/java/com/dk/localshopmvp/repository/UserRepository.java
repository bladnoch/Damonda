package com.dk.localshopmvp.repository;

import com.dk.localshopmvp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 *packageName    : com.dk.localshopmvp.repository
 * fileName       : UserRepository
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
