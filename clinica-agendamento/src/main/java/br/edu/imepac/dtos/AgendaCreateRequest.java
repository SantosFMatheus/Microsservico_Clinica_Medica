package br.edu.imepac.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaCreateRequest {
    private Long medicoId;
    private Long pacienteId;
    private Long atendenteId;
    private LocalDate data;
    private LocalTime hora;
}
