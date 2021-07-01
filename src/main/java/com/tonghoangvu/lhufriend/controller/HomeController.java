package com.tonghoangvu.lhufriend.controller;

import com.tonghoangvu.lhufriend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/account")
    public String accountPage(Model model) {
        model.addAttribute("email", authenticationService.getEmail());
        return "account";
    }
}
