package com.mademil.ferramentaria.config;

import com.mademil.ferramentaria.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/ficha/**").permitAll()
                .requestMatchers("/css/**", "/scripts/**", "/imagem/**" , "/icons/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/formulario-ficha", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  
                .logoutSuccessUrl("/login?logout")  
                .permitAll()
            );

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"));
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {    
        return new BCryptPasswordEncoder();
    }
}
