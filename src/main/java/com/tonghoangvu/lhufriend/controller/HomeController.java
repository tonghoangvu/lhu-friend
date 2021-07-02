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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView updateInfo(@Valid UserUpdateForm userUpdateForm) {
        String email = authenticationService.getEmail();
        userService.updateUserInfo(email, userUpdateForm);
        return new RedirectView("/account");
    }
}
