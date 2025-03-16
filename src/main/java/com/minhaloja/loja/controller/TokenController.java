package com.minhaloja.loja.controller;

import com.minhaloja.loja.controller.dto.LoginRequest;
import com.minhaloja.loja.controller.dto.LoginResponse;
import com.minhaloja.loja.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {
    private final JwtEncoder jwtEncoder;

    public TokenController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        if (!loginRequest.username().equals("rodrigo") || !loginRequest.password().equals("123")) {
            throw new BadCredentialsException("usuario ou senha invalidos");
        }
        User user = new User();
        user.setUserId(1L);
        user.setUsername("rodrigo");
        user.setPassword("123");

        var now = Instant.now();
        var expiresIn = 300L;


        var claims = JwtClaimsSet.builder()
                .issuer("loja")
                .subject(user.getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
