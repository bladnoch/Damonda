package com.dk.localshopmvp.service;

import com.dk.localshopmvp.dto.OrderRequestDto;
import com.dk.localshopmvp.entity.CartItem;
import com.dk.localshopmvp.entity.Order;
import com.dk.localshopmvp.entity.OrderItem;
import com.dk.localshopmvp.entity.User;
import com.dk.localshopmvp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *packageName    : com.dk.localshopmvp.service
 * fileName       : OrderService
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;

    public Order placeOrder(List<CartItem> cartItems) {
        if (cartItems.isEmpty()) throw new IllegalArgumentException("장바구니가 비어 있습니다.");

        List<OrderItem> orderItems = cartItems.stream()
                .map(ci -> OrderItem.of(productService.findById(ci.getProductId()), ci.getQuantity()))
                .collect(Collectors.toList());

        Order order = Order.create(orderItems);
        return orderRepository.save(order);
    }

    public Order createOrderWithUserInfo(User user, OrderRequestDto form, List<CartItem> cartItems) {
        if (cartItems.isEmpty()) throw new IllegalArgumentException("장바구니가 비어 있습니다.");

        List<OrderItem> orderItems = cartItems.stream()
                .map(ci -> OrderItem.of(productService.findById(ci.getProductId()), ci.getQuantity()))
                .collect(Collectors.toList());

        Order order = Order.create(orderItems);

        // 주문자 정보 추가
        order.setRecipientName(form.getRecipientName());
        order.setPhone(form.getPhone());
        order.setEmail(form.getEmail());
        order.setAddress(form.getAddress());

        return orderRepository.save(order);
    }

    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByEmail(user.getEmail()); // 또는 userId 기준도 가능
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 주문을 찾을 수 없습니다: " + id));
    }
}
