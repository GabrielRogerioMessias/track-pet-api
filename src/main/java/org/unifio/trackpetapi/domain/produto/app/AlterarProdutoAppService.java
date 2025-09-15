package org.unifio.trackpetapi.domain.produto.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unifio.trackpetapi.domain.produto.model.Produto;
import org.unifio.trackpetapi.domain.produto.repository.ProdutoDomainRepository;
import org.unifio.trackpetapi.domain.produto.usecase.AlterarProdutoUseCase;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AlterarProdutoAppService implements AlterarProdutoUseCase {

    private final ProdutoDomainRepository repository;

    @Override
    public void handle(AlterarProduto command) {

        Produto produto = repository.get(command.getId());

        produto.alterar()
            .nome(command.getNome())
            .valor(command.getValor())
            .nomeDuplicateConstraint(repository::existsByNomeAndIdNot)
            .apply();

        repository.save(produto);
    }

}
