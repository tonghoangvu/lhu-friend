package com.tonghoangvu.lhufriend.service;

import com.tonghoangvu.lhufriend.UserRepository;
import com.tonghoangvu.lhufriend.entity.User;
import com.tonghoangvu.lhufriend.exception.ErrorCode;
import com.tonghoangvu.lhufriend.exception.ViewException;
import com.tonghoangvu.lhufriend.model.UserUpdateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void signupOidcUser(OidcUser oidcUser) {
        var user = new User();
        user.setEmail(oidcUser.getEmail());
        user.setFullName(oidcUser.getFullName());
        user.setPictureUrl(oidcUser.getPicture());
        user.setLocale(oidcUser.getLocale());
        userRepository.save(user);
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new ViewException(ErrorCode.ACCOUNT_NOT_FOUND, "Tài khoản không tồn tại");
        return user;
    }

    public User updateUserInfo(String email, UserUpdateForm userUpdateForm) {
        User user = getUser(email);
        user.setFullName(userUpdateForm.getFullName());
        return userRepository.save(user);
    }
}
