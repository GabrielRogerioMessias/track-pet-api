package org.unifio.trackpetapi.domain.produto.usecase;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.Builder;
import lombok.Value;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface AlterarProdutoUseCase {

    void handle(AlterarProduto command);

    @Value
    @Builder
    class AlterarProduto {

        ProdutoId id;

        @Valid
        @NotNull(message = "Nome Produto Obrigatorio")
        String nome;

        @Valid
        @NotNull(message = "Valor Produto Obrigatorio")
        BigDecimal valor;


        public AlterarProduto from(ProdutoId id) {
            return AlterarProduto.builder()
                .id(requireNonNull(id))
                .nome(nome)
                .valor(valor)
                .build();
        }
    }
}
