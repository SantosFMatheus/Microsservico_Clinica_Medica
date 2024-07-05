package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "especialidades")
@Data
public class EspecialidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;  // Renomeado para consistência com a definição anterior

    @ManyToMany(mappedBy = "especialidades")
    private Set<MedicoModel> medicos;
}
