package com.minhaloja.loja.controller.dto;

import java.math.BigDecimal;

public record IncluirProdutoDTO(String nome, String descricao, BigDecimal valorUnitario) {
}
