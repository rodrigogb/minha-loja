package com.minhaloja.loja.controller;

import com.minhaloja.loja.controller.dto.IncluirProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProdutoController {

    @PostMapping("/produtos")
    public ResponseEntity<Void> incluirProduto(@RequestBody IncluirProdutoDTO dto, JwtAuthenticationToken token) {
        // Obtendo o objeto Jwt
        Jwt jwt = token.getToken();

        // Pegando o "sub" (subject) do JWT, que geralmente contém o nome do usuário
        String nomeUsuario = jwt.getClaim("sub");

        System.out.println("Nome do usuário extraído: " + nomeUsuario);

        if (!"rodrigo".equals(nomeUsuario)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();

    }
}