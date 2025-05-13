package com.dk.localshopmvp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.entity
 * fileName       : Product
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Entity
@Getter
@Setter
public class Product {

    @Id @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;
    private String imagePath;

    public Product() {
    }

    public static Product of(String name, String description, BigDecimal price, Integer stockQuantity, String imagePath) {
        Product p = new Product();
        p.name = name;
        p.description = description;
        p.price = price;
        p.stockQuantity = stockQuantity;
        p.imagePath = imagePath;
        return p;
    }
}

