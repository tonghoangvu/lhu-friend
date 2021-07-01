package com.tonghoangvu.lhufriend.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
                            FilterChain chain, Authentication authentication)
                            throws IOException, ServletException {
                        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                        System.out.println(oidcUser.getEmail());
                        super.onAuthenticationSuccess(request, response, chain, authentication);
                    }
                })
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }
}
