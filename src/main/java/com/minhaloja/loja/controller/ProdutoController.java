package com.minhaloja.loja.controller;

import com.minhaloja.loja.controller.dto.IncluirProdutoDTO;
import com.minhaloja.loja.model.Produto;
import com.minhaloja.loja.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

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

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }

}