package com.dk.localshopmvp.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem {
    private UUID productId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String imagePath;

    private CartItem(UUID productId, String name, BigDecimal price, int quantity, String imagePath) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    // ✅ 정적 팩토리 메서드
    public static CartItem of(UUID productId, String name, BigDecimal price, int quantity, String imagePath) {
        return new CartItem(productId, name, price, quantity, imagePath);
    }

    // 🔄 오버로드: imagePath 없이 생성할 경우 (선택)
    public static CartItem of(UUID productId, String name, BigDecimal price, int quantity) {
        return new CartItem(productId, name, price, quantity, null);
    }

    // ✅ Getter 및 Setter
    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
