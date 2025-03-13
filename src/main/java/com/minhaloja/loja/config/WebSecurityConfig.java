package com.minhaloja.loja.config;

import com.minhaloja.loja.service.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Define a cadeia de filtros que o Spring Security aplicará para proteger as rotas da aplicação.
    // As regras de segurança especificam quais rotas precisam de autenticação e quais são públicas (permitidas sem autenticação).
    // Neste caso, a rota "/health" está acessível sem autenticação (permitAll),
    // enquanto qualquer outra rota (anyRequest()) exigirá autenticação (authenticated).
    // Também é configurado o uso da autenticação básica HTTP (httpBasic) para a aplicação.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/health").permitAll() // Permite acesso à rota "/health" sem autenticação
                        .anyRequest().authenticated()           // Exige autenticação para todas as outras rotas
                )
                .httpBasic(Customizer.withDefaults());          // Configura autenticação básica HTTP para a aplicação

        return http.build();
    }

    // Define o AuthenticationManager, que será responsável pela autenticação de usuários na aplicação.
    // O AuthenticationManager usa o AuthenticationManagerBuilder para configurar como a autenticação será realizada.
    // Como o Spring Security usa o UserDetailsService para carregar informações sobre os usuários, aqui estamos passando
    // a nossa implementação personalizada (AuthUserService), que já implementa o UserDetailsService.
    // Não é necessário utilizar uma implementação padrão, pois a AuthUserService já fornece a lógica para carregar o usuário.
    // Também é configurado o PasswordEncoder (BCryptPasswordEncoder) para garantir que a senha seja validada corretamente.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, AuthUserService authUserService) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authUserService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

