package com.tonghoangvu.lhufriend.model;

import com.tonghoangvu.lhufriend.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfo {
    private String email;
    private String fullName;
    private String pictureUrl;
    private String locale;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserInfo(User user) {
        email = user.getEmail();
        fullName = user.getFullName();
        pictureUrl = user.getPictureUrl();
        locale = user.getLocale();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
    }
}
