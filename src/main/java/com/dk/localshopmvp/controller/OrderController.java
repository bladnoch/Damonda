package com.dk.localshopmvp.controller;

import com.dk.localshopmvp.dto.OrderRequestDto;
import com.dk.localshopmvp.entity.CartItem;
import com.dk.localshopmvp.entity.Order;
import com.dk.localshopmvp.entity.User;
import com.dk.localshopmvp.service.ExcelService;
import com.dk.localshopmvp.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.controller
 * fileName       : OrderController
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final ExcelService excelService;

    @PostMapping("/create")
    public String createOrder(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        Order order = orderService.placeOrder(cart);
        session.setAttribute("cart", new ArrayList<CartItem>()); // 장바구니 비우기
        model.addAttribute("order", order);
        return "order/orderComplete";
    }

    @GetMapping("/new")
    public String showOrderForm(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        // 주문 폼 DTO 초기값 세팅
        OrderRequestDto form = new OrderRequestDto();
        form.setRecipientName(loginUser.getName());
        form.setPhone(loginUser.getPhone());
        form.setEmail(loginUser.getEmail());

        model.addAttribute("orderForm", form);
        model.addAttribute("cartItems", cart);

        BigDecimal totalPrice = cart.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalPrice", totalPrice);

        return "order/orderForm";
    }

    @PostMapping("/confirm")
    public String confirmOrder(OrderRequestDto form, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        // 주문 생성
        Order order = orderService.createOrderWithUserInfo(loginUser, form, cart);

        // 세션 장바구니 비우기
        session.setAttribute("cart", new ArrayList<CartItem>());

        // 모델에 주문 전달
        model.addAttribute("order", order);
        return "order/orderComplete";
    }

    @GetMapping("/history")
    public String viewOrderHistory(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.findOrdersByUser(loginUser);
        model.addAttribute("orders", orders);
        return "order/orderHistory";
    }



    @GetMapping("/{orderId}/excel")
    public ResponseEntity<byte[]> downloadOrderExcel(@PathVariable UUID orderId) throws IOException {
        System.out.println("💡 엑셀 다운로드 요청: " + orderId);

        Order order = orderService.findById(orderId); // 여기서 예외 터질 가능성 있음
        System.out.println("✅ 주문 조회 완료: " + order.getRecipientName());

        byte[] excelData = excelService.generateOrderExcel(order);
        System.out.println("✅ 엑셀 생성 완료");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("order_" + orderId + ".xlsx").build());

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }
}
