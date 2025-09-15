package org.unifio.trackpetapi.domain.produto.usecase;

import java.math.BigDecimal;

import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.Builder;
import lombok.Value;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface RegistrarProdutoUseCase {

    ProdutoId handle(RegistrarProduto command);


    @Value
    @Builder
    class RegistrarProduto {

        @Valid
        @NotNull(message = "Nome do produto é obrigatório")
        String nome;

        @Valid
        @NotNull(message = "Valor do produto é obrigatório")
        BigDecimal valor;

    }

}
