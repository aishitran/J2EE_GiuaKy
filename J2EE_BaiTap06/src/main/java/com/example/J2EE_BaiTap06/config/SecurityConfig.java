package com.example.J2EE_BaiTap06.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
    .authorizeHttpRequests(auth -> auth

            .requestMatchers("/admin/**").hasRole("ADMIN")

            .requestMatchers("/enroll/**").hasRole("STUDENT")

            .requestMatchers("/courses","/","/home","/register","/login").permitAll()

            .anyRequest().authenticated()
    )

    .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
    )

    .logout(logout -> logout
            .logoutSuccessUrl("/login")
            .permitAll()
    );

    return http.build();
}

@Bean
public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();

}

}