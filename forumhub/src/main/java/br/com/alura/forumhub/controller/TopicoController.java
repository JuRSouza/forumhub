package br.com.alura.forumhub.controller;


import br.com.alura.forumhub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController // classe é um controlador REST, responde a requisições HTTP em formato JSON
@RequestMapping("/topicos")// é caminho base para todas as requisições, todas as rotas vão começar com "/topicos"
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService service;

    @Transactional
    @PostMapping // Mapeia uma requisição HTTP POST para esse método
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        var topico = service.cadastrar(dados);
        var dto = new DadosDetalhamentoTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getAutor().getNome(), topico.getStatus());
        URI uri = URI.create("/topicos/" + topico.getId());
        return ResponseEntity.created(uri).body(dto);
        //@RequestBody
        // Indica que o corpo da requisição será convertido para um objeto Java, recebe dados em formato JSON

        // @Valid
        // Solicita que o objeto recebido seja validado automaticamente com base em suas anotações (ex: @NotNull)
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pageable) {
        var lista = repository.findAll(pageable)
                .map(t -> new DadosDetalhamentoTopico(t.getId(), t.getTitulo(), t.getMensagem(),
                        t.getDataCriacao(), t.getAutor().getNome(), t.getStatus()))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var topico = repository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        var dto = new DadosDetalhamentoTopico(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getAutor().getNome(), topico.getStatus());
        return ResponseEntity.ok(dto);
    }
        //@PathVariable
        // Indica que o valor virá da URL, como em /topicos/{id}


    @PutMapping("/{id}")//Mapeia uma requisição HTTP PUT para esse método, atualiza um recurso existente
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName(); // pega email do usuário logado

        var topico = service.atualizar(id, dados, emailUsuario);
        var detalhamento = new DadosDetalhamentoTopico(topico);
        return ResponseEntity.ok(detalhamento);
    }

    @DeleteMapping("/{id}")//Mapeia uma requisição HTTP DELETE para esse método
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
