package com.tonghoangvu.lhufriend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public OidcUser getUser() {
        Authentication auth = getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof OidcUser oidcUser)
            return oidcUser;
        return null;
    }

    public String getEmail() {
        OidcUser user = getUser();
        return user == null ? null : user.getEmail();
    }
}
