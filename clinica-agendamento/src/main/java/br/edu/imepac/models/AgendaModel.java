package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendas")
@Data
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;

    private Long atendenteId;
    private LocalDate data;
    private LocalTime hora;
}
