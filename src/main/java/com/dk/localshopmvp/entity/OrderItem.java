package com.dk.localshopmvp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.entity
 * fileName       : OrderItem
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int quantity;

    private BigDecimal price; // 단가 (주문 당시의 가격)

    public static OrderItem of(Product product, int quantity) {
        OrderItem item = new OrderItem();
        item.product = product;
        item.quantity = quantity;
        item.price = product.getPrice(); // 주문 시점 가격 고정
        return item;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}


