package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id,
                                      String titulo,
                                      String mensagem,
                                      LocalDateTime dataCriacao,
                                      String autor,
                                      StatusTopico status) {


    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getAutor().getNome(), topico.getStatus());
    }
}
//No video do professor
//id
//titulo
//mensagem
// data criacao
// autor
// status
//respostas []

