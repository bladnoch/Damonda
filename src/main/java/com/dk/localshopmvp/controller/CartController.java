package com.dk.localshopmvp.controller;

import com.dk.localshopmvp.entity.CartItem;
import com.dk.localshopmvp.entity.Product;
import com.dk.localshopmvp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.controller
 * fileName       : CartController
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
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return "cart/cartView";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam UUID productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        Product product = productService.findById(productId);
        List<CartItem> cart = getCart(session);

        // 중복 검사 + 수량 증가
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return "redirect:/cart";
            }
        }

        cart.add(CartItem.of(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                product.getImagePath()  // ✅ imagePath도 함께 저장
        ));

        return "redirect:/cart";
    }


    @PostMapping("/remove")
    public String removeFromCart(@RequestParam UUID productId, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.setAttribute("cart", new ArrayList<CartItem>());
        return "redirect:/cart";
    }

    // 장바구니 가져오기
    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
