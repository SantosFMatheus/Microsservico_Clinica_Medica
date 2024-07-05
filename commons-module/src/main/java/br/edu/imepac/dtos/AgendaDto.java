package br.edu.imepac.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaDto {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private Long funcionarioId;
    private LocalDate data;
    private LocalTime hora;
}