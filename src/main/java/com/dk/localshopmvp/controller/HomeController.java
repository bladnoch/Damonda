package com.dk.localshopmvp.controller;

import com.dk.localshopmvp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.dk.localshopmvp.controller
 * fileName       : HomeController
 * author         : doungukkim
 * date           : 2025. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 12.        doungukkim       최초 생성
 */
@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }
}
