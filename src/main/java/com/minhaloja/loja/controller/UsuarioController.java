package com.minhaloja.loja.controller;


import com.minhaloja.loja.model.Usuario;
import com.minhaloja.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    /*
    @Autowired
    private UsuarioService usuarioService;

    // Criar um novo usuário
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    // Buscar usuário pelo nome
    @GetMapping("/{nome}")
    public Usuario buscarUsuario(@PathVariable String nome) {
        return usuarioService.buscarPorNome(nome);
    }

     */
}