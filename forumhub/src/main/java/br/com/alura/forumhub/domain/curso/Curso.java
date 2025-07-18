package br.com.alura.forumhub.domain.curso;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String categoria;

    // Ver se vai precisar da Entidade Perfil e Resposta
    //Tem que fazer as migrations
}
