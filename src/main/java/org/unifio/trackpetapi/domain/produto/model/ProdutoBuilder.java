package org.unifio.trackpetapi.domain.produto.model;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.function.BiPredicate;

import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.Getter;

@Getter
public class ProdutoBuilder {

    private ProdutoId id;

    private String nome;

    private BigDecimal valor;

    private BiPredicate<String, ProdutoId> nomeDuplicateConstraint;


    public ProdutoBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoBuilder valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public ProdutoBuilder nomeDuplicateConstraint(BiPredicate<String, ProdutoId> nomeDuplicateConstraint) {
        this.nomeDuplicateConstraint = nomeDuplicateConstraint;
        return this;
    }

    public Produto build() {

        id = ProdutoId.generate();

        if(nomeDuplicateConstraint.test(nome, id)){
            throw new RuntimeException();
        }

        return new Produto(this);
    }

}
