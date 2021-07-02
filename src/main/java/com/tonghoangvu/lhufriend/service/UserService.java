package com.tonghoangvu.lhufriend.service;

import com.tonghoangvu.lhufriend.UserRepository;
import com.tonghoangvu.lhufriend.entity.User;
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
        return userRepository.findByEmail(email);
    }
}
