package com.dk.localshopmvp.entity;

import com.dk.localshopmvp.service.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.entity
 * fileName       : Payment
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    private String depositorName;

    private String bankName;

    private String accountNumber;

    private BigDecimal amount;

    private LocalDateTime depositTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, CONFIRMED
}

