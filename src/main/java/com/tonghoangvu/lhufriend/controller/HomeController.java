package com.tonghoangvu.lhufriend.controller;

import com.tonghoangvu.lhufriend.entity.User;
import com.tonghoangvu.lhufriend.model.UserInfo;
import com.tonghoangvu.lhufriend.model.UserUpdateForm;
import com.tonghoangvu.lhufriend.service.AuthenticationService;
import com.tonghoangvu.lhufriend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/account")
    public String accountPage(Model model) {
        String email = authenticationService.getEmail();
        User user = userService.getUser(email);
        model.addAttribute("userInfo", new UserInfo(user));
        return "account";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/account")
    public String updateInfo(
            @Valid UserUpdateForm userUpdateForm,
            BindingResult bindingResult,
            Model model) {
        String email = authenticationService.getEmail();
        User user;
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", errorMessage);
            user = userService.getUser(email);
        } else
            user = userService.updateUserInfo(email, userUpdateForm);
        model.addAttribute("userInfo", new UserInfo(user));
        return "account";
    }
}
