package com.dk.localshopmvp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.dk.localshopmvp.dto
 * fileName       : OrderRequestDto
 * author         : doungukkim
 * date           : 2025. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 13.        doungukkim       최초 생성
 */
@Setter
@Getter
public class OrderRequestDto {
    private String recipientName;
    private String phone;
    private String email;
    private String address;
}
