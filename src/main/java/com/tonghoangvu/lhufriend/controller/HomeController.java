package com.tonghoangvu.lhufriend.controller;

import com.tonghoangvu.lhufriend.entity.Student;
import com.tonghoangvu.lhufriend.entity.User;
import com.tonghoangvu.lhufriend.model.StudentInfo;
import com.tonghoangvu.lhufriend.model.UserInfo;
import com.tonghoangvu.lhufriend.model.UserUpdateForm;
import com.tonghoangvu.lhufriend.service.AuthenticationService;
import com.tonghoangvu.lhufriend.service.StudentService;
import com.tonghoangvu.lhufriend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final StudentService studentService;

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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/student")
    public String studentPage(@RequestParam(defaultValue = "0") int page, Model model) {
        final int SIZE = 10;
        long totalPage = studentService.getStudentCount();
        if (totalPage % SIZE == 0)
            totalPage /= SIZE;
        else
            totalPage = totalPage / SIZE + 1;
        List<Student> students = studentService.getStudents(page, SIZE);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("students", students.stream()
                .map(StudentInfo::new)
                .collect(Collectors.toList()));
        return "student";
    }
}
