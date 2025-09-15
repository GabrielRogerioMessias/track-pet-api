package org.unifio.trackpetapi.domain.produto.model;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.Getter;

@Getter
public class ProdutoBuilderUpdate {

    private final Consumer<ProdutoBuilderUpdate> action;

    private final ProdutoId id;

    private String nome;

    private BigDecimal valor;

    private BiPredicate<String, ProdutoId> nomeDuplicateConstraint;

    ProdutoBuilderUpdate(ProdutoId id, Consumer<ProdutoBuilderUpdate> action) {
        this.id = requireNonNull(id);
        this.action = requireNonNull(action);
    }

    public void apply() {

        if(nomeDuplicateConstraint.test(nome, id)) {
            throw new RuntimeException();
        }

        action.accept(this);
    }

    public ProdutoBuilderUpdate nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoBuilderUpdate valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public ProdutoBuilderUpdate nomeDuplicateConstraint(
        BiPredicate<String, ProdutoId> nomeDuplicateConstraint) {
        this.nomeDuplicateConstraint = nomeDuplicateConstraint;
        return this;
    }

}
