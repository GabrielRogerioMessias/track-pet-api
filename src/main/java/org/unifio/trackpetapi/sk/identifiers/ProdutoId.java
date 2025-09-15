package org.unifio.trackpetapi.sk.identifiers;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

import jakarta.persistence.Embeddable;

@SuppressWarnings("squid:S2160")
@Getter
@Embeddable
public final class ProdutoId implements Serializable {

    private static final long serialVersionUID = 4523063475029927590L;

    public static final String ATTR = "value";

    public static final ProdutoId NAO_INFORMADO = new ProdutoId();

    @JsonValue
    private final UUID value;

    private ProdutoId() {
        this.value = null;
    }

    private ProdutoId(UUID value) {
        this.value = requireNonNull(value);
    }

    public static ProdutoId generate() {
        return new ProdutoId(UUID.randomUUID());
    }

    public static ProdutoId from(UUID uuid) {
        return new ProdutoId(uuid);
    }

    @JsonCreator
    public static ProdutoId from(String uuid) {
        return "".equalsIgnoreCase(uuid.trim()) ? NAO_INFORMADO : new ProdutoId(UUID.fromString(uuid));
    }

    public ProdutoIdException notFoundException() {
        return new ProdutoIdException();
    }

    public static class ProdutoIdException extends RuntimeException {
        public ProdutoIdException() {
            super("Produto Id inválido ou não encontrado.");
        }
    }

}

