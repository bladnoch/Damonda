package com.dk.localshopmvp.entity;

import com.dk.localshopmvp.service.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.entity
 * fileName       : Order
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    private UUID id;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // ✅ 주문자 정보 필드 추가
    private String recipientName;
    private String phone;
    private String email;
    private String address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public static Order create(List<OrderItem> items) {
        Order order = new Order();
        order.orderTime = LocalDateTime.now();
        order.status = OrderStatus.ORDERED;
        for (OrderItem item : items) {
            item.setOrder(order);
        }
        order.orderItems.addAll(items);
        return order;
    }

    public BigDecimal getTotalPrice() {
        return orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}


