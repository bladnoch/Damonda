package com.dk.localshopmvp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.dk.localshopmvp.dto
 * fileName       : UserSignupForm
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Getter
@Setter
public class UserSignupForm {
    private String email;
    private String password;
    private String name;
    private String phone;
}

