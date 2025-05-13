package com.dk.localshopmvp.repository;

import com.dk.localshopmvp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.repository
 * fileName       : OrderRepository
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByEmail(String email);
}

