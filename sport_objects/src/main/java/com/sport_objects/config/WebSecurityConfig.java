package com.sport_objects.config;

import com.sport_objects.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers("/registration").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/sport-type").permitAll()
                .requestMatchers("/team").permitAll()

                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/sport-type/**").hasRole("ADMIN")

                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/css/**").permitAll()

                .anyRequest().permitAll()

                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()

                .and()
                .logout().permitAll().logoutSuccessUrl("/");

        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}
