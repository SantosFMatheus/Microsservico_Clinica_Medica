package br.edu.imepac.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaDto {
    private Long id;
    private Long medicoId;
    private String medicoNome;
    private Long pacienteId;
    private String pacienteNome;
    private Long atendenteId;
    private LocalDate data;
    private LocalTime hora;
}
