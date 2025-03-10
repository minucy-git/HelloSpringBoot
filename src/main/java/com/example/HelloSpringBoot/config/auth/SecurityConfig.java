package com.example.HelloSpringBoot.config.auth;

import com.example.HelloSpringBoot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http
                .csrf(csrf->{
                    csrf.disable();
                    System.out.println("CSRF");
                })
                .headers(headers->{
                    headers.frameOptions(
                            frame->{
                                frame.disable();
                                System.out.println("FRAME");
                            });
                    System.out.println("HEADERS");
                })
                .authorizeHttpRequests(authorize->{
                    authorize
                            .requestMatchers(
                                    "/",
                                    "/css/**",
                                    "/images/**",
                                    "/js/**",
                                    "/h2-console/**").permitAll()
                            .requestMatchers(
                                    "/api/v1/**").hasRole(Role.USER.name())
                            .anyRequest().authenticated();
                    System.out.println("AUTHORIZE");
                })
                .logout(logout->{
                    logout.logoutSuccessUrl("/");
                    System.out.println("LOGOUT");
                })
                .oauth2Login(auth2->{
                    auth2
                            .userInfoEndpoint(userInfo->{
                                userInfo
                                        .userService(customOAuth2UserService);
                                System.out.println("USERINFO");
                            });
                    System.out.println("AUTH2");
                });

        return http.build();

    }

}
