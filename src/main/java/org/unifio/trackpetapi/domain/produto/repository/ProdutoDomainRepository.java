package org.unifio.trackpetapi.domain.produto.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.unifio.trackpetapi.domain.produto.model.Produto;
import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

public interface ProdutoDomainRepository extends Repository<Produto, ProdutoId> {

    void save(Produto produto);

    boolean existsByNomeAndIdNot(String nome, ProdutoId id);

    Optional<Produto> findById(ProdutoId id);

    default Produto get(ProdutoId id) {
        return findById(id).orElseThrow(id::notFoundException);
    }

}
