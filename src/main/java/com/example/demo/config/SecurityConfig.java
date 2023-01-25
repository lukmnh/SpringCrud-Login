package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    // @Autowired
    // UserDetailsService userDetailsService;

    // @Bean
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.userDetailsService(userDetailsService);
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Role : Director, Manager, Staff
        // handle attack Cross Site Request Forgery
        http.csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth
                        .antMatchers("/user-management/**").permitAll()
                        .antMatchers("/role/**").permitAll()
                        .antMatchers("/division").permitAll()
                        .antMatchers("/employee/**").permitAll()
                        .antMatchers("/region/**").hasAuthority("Staff")
                        // .anyRequest()
                        // .authenticated()
                        // .httpBasic
                        .and()
                        .formLogin()
                        .loginPage("/user-management/login")
                        .loginProcessingUrl("/check")
                        .and()
                        .logout();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();

    }

    @Bean
    public PasswordEncoder PasswordHashing() {
        return new BCryptPasswordEncoder();
    }
}
