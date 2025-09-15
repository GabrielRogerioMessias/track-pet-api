package org.unifio.trackpetapi.adapters.in.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unifio.trackpetapi.domain.produto.usecase.AlterarProdutoUseCase;
import org.unifio.trackpetapi.domain.produto.usecase.AlterarProdutoUseCase.AlterarProduto;
import org.unifio.trackpetapi.domain.produto.usecase.RegistrarProdutoUseCase;
import org.unifio.trackpetapi.domain.produto.usecase.RegistrarProdutoUseCase.RegistrarProduto;
import org.unifio.trackpetapi.sk.identifiers.ProdutoId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor


@RestController
@RequestMapping(path = "/api/v1/produtos", produces = APPLICATION_JSON_VALUE)
public class ProdutoController {

    private final RegistrarProdutoUseCase registrarProdutoService;
    private final AlterarProdutoUseCase alterarProdutoService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registrar(@RequestBody RegistrarProduto cmd) {

        ProdutoId id = registrarProdutoService.handle(cmd);

        return ResponseEntity.created(fromCurrentRequest()
                .path("/").path(id.getValue().toString()).build().toUri())
                .build();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void alterar(@PathVariable ProdutoId id, @RequestBody AlterarProduto cmd) {
        alterarProdutoService.handle(cmd.from(id));
    }

}
