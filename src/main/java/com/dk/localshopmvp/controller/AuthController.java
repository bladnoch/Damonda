package com.dk.localshopmvp.controller;

import com.dk.localshopmvp.dto.UserLoginForm;
import com.dk.localshopmvp.dto.UserSignupForm;
import com.dk.localshopmvp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * packageName    : com.dk.localshopmvp.controller
 * fileName       : AuthController
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
public class AuthController {

    private final UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupForm", new UserSignupForm());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("signupForm") UserSignupForm form) {
        userService.signup(form);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new UserLoginForm());
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") UserLoginForm form,
                               HttpSession session,
                               Model model) {
        return userService.login(form.getEmail(), form.getPassword())
                .map(user -> {
                    session.setAttribute("loginUser", user); // 세션에 저장
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "이메일 또는 비밀번호가 일치하지 않습니다.");
                    return "auth/login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }


}


