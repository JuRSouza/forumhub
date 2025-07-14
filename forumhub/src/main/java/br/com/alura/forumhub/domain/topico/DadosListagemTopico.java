package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime dataCriacao) {
}
//No video do professor
//id
// titulo
// mensagem
//dataCriacao
