package com.tonghoangvu.lhufriend.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdateForm {
    @NotBlank(message = "Full name is required")
    private String fullName;
}
