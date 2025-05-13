package com.dk.localshopmvp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.dk.localshopmvp.dto
 * fileName       : UserLoginForm
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
public class UserLoginForm {
    private String email;
    private String password;
}

