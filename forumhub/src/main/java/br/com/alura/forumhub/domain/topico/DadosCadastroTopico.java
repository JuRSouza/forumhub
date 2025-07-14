package br.com.alura.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(@NotBlank String titulo,
                                  @NotBlank String mensagem,
                                  @NotBlank String curso) {
}

// No vídeo do professor
//Mensagem
//nome do curso
//titulo
