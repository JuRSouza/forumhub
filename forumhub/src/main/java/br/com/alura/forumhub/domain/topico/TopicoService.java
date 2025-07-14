package br.com.alura.forumhub.domain.topico;


import br.com.alura.forumhub.domain.curso.Curso;
import br.com.alura.forumhub.domain.curso.CursoRepository;
import br.com.alura.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service // indica ao Spring que esta calsse é um service- contém regras de negócio
public class TopicoService {

    @Autowired//Faz a injeção de dependencias
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional //Garante que o método ou classe será executado
    public Topico cadastrar(DadosCadastroTopico dados) {
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Já existe um tópico com o mesmo título e mensagem.");
        }

        Curso curso = cursoRepository.findByNome(dados.curso());
        if (curso == null) {
            throw new RuntimeException("Curso não encontrado");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Usuario autor = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(StatusTopico.NAO_RESPONDIDO);
        topico.setAutor(autor);

        return repository.save(topico);
    }

    @Transactional
    public Topico atualizar(Long id, @Valid DadosAtualizacaoTopico dados, String autorRequest){
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()){
            throw new RuntimeException("Tpópico não encontrado.");
        }
        var topico = topicoOptional.get();

        if (!topico.getAutor().getEmail().equals(autorRequest)){
            throw new RuntimeException("Você não tem permissão para atualizar este tópico");
        }
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        return topico;
    }

    @Transactional
    public void excluir(Long id){
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()){
            throw new RuntimeException("Tópico não encontrado");
        }
        var topico = topicoOptional.get();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Usuario autor = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!topico.getAutor().getEmail().equals(autor.getEmail())){
            throw new RuntimeException("Você não tem permissão para excluir este tópico.");
        }

        repository.deleteById(id);
    }


}

