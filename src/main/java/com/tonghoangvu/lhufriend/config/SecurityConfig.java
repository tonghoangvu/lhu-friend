package com.tonghoangvu.lhufriend.config;

import com.tonghoangvu.lhufriend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .oauth2Login()
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(
                            HttpServletRequest request, HttpServletResponse response,
                            Authentication authentication)
                            throws IOException, ServletException {
                        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                        if (!userService.isEmailExists(oidcUser.getEmail()))
                            userService.signupOidcUser(oidcUser);
                        super.onAuthenticationSuccess(request, response, authentication);
                    }
                })
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }
}
