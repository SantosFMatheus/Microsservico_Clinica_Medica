package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "atendimentos")
@Data
public class AtendimentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteModel paciente;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String historico;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String receituario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exames;
}