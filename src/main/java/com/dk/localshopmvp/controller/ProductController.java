package com.dk.localshopmvp.controller;

import com.dk.localshopmvp.dto.ProductCreateForm;
import com.dk.localshopmvp.entity.Product;
import com.dk.localshopmvp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.controller
 * fileName       : ProductController
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
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable UUID id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/productDetail";
    }

    @GetMapping("/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("productForm", new ProductCreateForm());
        return "product/productForm";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("productForm") ProductCreateForm form,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.register(form, imageFile);
        return "redirect:/";
    }


    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/productList";
    }

}

