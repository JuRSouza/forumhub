package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.curso.Curso;
import br.com.alura.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")//nome da tabela como topicos
@Entity(name = "Topico")// indica que a classe é umaentidade JPA, e será mapeada para uma tabela no banco
@Getter// Cria automaticamente os métodos getter para todos os atributos
@Setter //Cria automaticamente os métodos setter para todos os atributos da classe
@NoArgsConstructor // gera construtor sem argumentos
@AllArgsConstructor // gera construtor com todos os atributos
@EqualsAndHashCode(of = "id") //Gera os métodos equals() e hashCode(), considerando apenas o campo "id".
public class Topico {

    @Id// é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)// a chave primária será gerada automaticamente, com o IDENTITY que faz o auto-incremento
    private  Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao =LocalDateTime.now();

    @Enumerated(EnumType.STRING)//atributo do tipo enum será armazenado como uma String no banco de dados
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

}
