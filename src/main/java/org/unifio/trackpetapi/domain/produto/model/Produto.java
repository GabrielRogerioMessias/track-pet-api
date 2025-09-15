package org.unifio.trackpetapi.domain.produto.model;

import static lombok.AccessLevel.PRIVATE;
import static java.util.Objects.requireNonNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


import org.hibernate.annotations.DynamicUpdate;
import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@SuppressWarnings("squid:S2160")

@Getter
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)

@DynamicUpdate
@Entity
public class Produto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3858016249175102973L;

    @EmbeddedId
    @AttributeOverride(name = ProdutoId.ATTR, column = @Column(name = "id"))
    private final ProdutoId id;

    private String nome;

    private BigDecimal valor;


    public static ProdutoBuilder builder() {
        return new ProdutoBuilder();
    }

    Produto(ProdutoBuilder builder) {
        this.id = requireNonNull(builder.getId());
        this.nome = requireNonNull(builder.getNome());
        this.valor = requireNonNull(builder.getValor());
    }

    public ProdutoBuilderUpdate alterar() {
        return new ProdutoBuilderUpdate(id, form -> {
            this.nome = requireNonNull(form.getNome());
            this.valor = requireNonNull(form.getValor());

        });
    }

}
