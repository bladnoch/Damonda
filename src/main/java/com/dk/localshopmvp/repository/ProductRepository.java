package com.dk.localshopmvp.repository;

import com.dk.localshopmvp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.repository
 * fileName       : ProductRepository
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
