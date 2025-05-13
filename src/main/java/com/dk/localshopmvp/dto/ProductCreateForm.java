package com.dk.localshopmvp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * packageName    : com.dk.localshopmvp.dto
 * fileName       : ProductFrom
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
public class ProductCreateForm {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    // 이미지 경로 저장용 필드
    private String imagePath;
}

