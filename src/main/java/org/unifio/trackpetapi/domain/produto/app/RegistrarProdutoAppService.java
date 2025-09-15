package org.unifio.trackpetapi.domain.produto.app;

import org.springframework.stereotype.Service;
import org.unifio.trackpetapi.domain.produto.model.Produto;
import org.unifio.trackpetapi.domain.produto.repository.ProdutoDomainRepository;
import org.unifio.trackpetapi.domain.produto.usecase.RegistrarProdutoUseCase;
import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor

@Service
@Transactional
public class RegistrarProdutoAppService implements RegistrarProdutoUseCase {

    private final ProdutoDomainRepository produtoDomainRepository;

    @Override
    public ProdutoId handle(RegistrarProduto command) {

        Produto caracteristicaTecnica = Produto.builder()
            .nome(command.getNome())
            .nomeDuplicateConstraint(produtoDomainRepository::existsByNomeAndIdNot)
            .valor(command.getValor())
            .build();

        produtoDomainRepository.save(caracteristicaTecnica);

        return caracteristicaTecnica.getId();
    }

}
