package com.assemblyconsultoria.baterponto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .requestMatchers("/", "/login").permitAll() // Permitir acesso à página de login
                .anyRequest().authenticated()         // Exigir autenticação para outras páginas
            .and()
            .formLogin()
                .loginPage("/")                       // Página de login
                .loginProcessingUrl("/login")         // Endpoint para processar o login
                .defaultSuccessUrl("/dashboard", true) // Redirecionar após sucesso
                .failureUrl("/?error=true")           // Redirecionar em caso de falha
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        var user1 = org.springframework.security.core.userdetails.User
            .withUsername("user@example.com")
            .password(passwordEncoder.encode("Senha@123"))
            .roles("USER")
            .build();

        var user2 = org.springframework.security.core.userdetails.User
            .withUsername("admin@example.com")
            .password(passwordEncoder.encode("Admin@123"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}

