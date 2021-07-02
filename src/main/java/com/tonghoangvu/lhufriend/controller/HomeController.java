package com.tonghoangvu.lhufriend.controller;

import com.tonghoangvu.lhufriend.entity.User;
import com.tonghoangvu.lhufriend.exception.ErrorCode;
import com.tonghoangvu.lhufriend.exception.ViewException;
import com.tonghoangvu.lhufriend.model.UserInfo;
import com.tonghoangvu.lhufriend.service.AuthenticationService;
import com.tonghoangvu.lhufriend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        if (user == null)
            throw new ViewException(ErrorCode.ACCOUNT_NOT_FOUND, "Tài khoản không tồn tại");
        model.addAttribute("userInfo", new UserInfo(user));
        return "account";
    }
}
