package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "especialidade")
@Data
public class EspecialidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Caso precise de uma descrição, pode adicionar assim:
    private String descricao;

    // Construtores, getters e setters são gerados automaticamente pelo Lombok
}
