package com.dk.localshopmvp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.entity
 * fileName       : User
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String password;
    private String name;
    private String phone;

    public static User of(String email, String password, String name, String phone) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;
        user.phone = phone;
        return user;
    }
}



