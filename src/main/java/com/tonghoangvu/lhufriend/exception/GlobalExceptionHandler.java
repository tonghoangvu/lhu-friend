package com.tonghoangvu.lhufriend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiExceptionResponse> handleApiException(ApiException e) {
        var response = new ApiExceptionResponse(e.getCode(), e.getMessage());
        return ResponseEntity
                .status(e.getCode().getStatus())
                .body(response);
    }

    @ExceptionHandler(ViewException.class)
    protected ModelAndView handleViewException(ViewException e) {
        var modelAndView = new ModelAndView("custom-error");
        modelAndView.setStatus(e.getCode().getStatus());
        modelAndView.addObject("code", e.getCode());
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleBadRequestException(Model model) {
        model.addAttribute("code", ErrorCode.BAD_REQUEST);
        model.addAttribute("message", "Bad Request");
        return "custom-error";
    }

    // Don't catch Exception or RuntimeException class
    // Because that also disables OAuth auto redirect back
    // And trigger AccessDeniedException, which hard to catch, solve and redirect
    // Only throw ApiException or ViewException in whole project
    // And Spring exceptions, just leave it alone
    // You got all it's messages in console
}
