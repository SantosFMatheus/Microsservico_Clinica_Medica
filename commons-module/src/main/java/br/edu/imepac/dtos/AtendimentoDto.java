package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class AtendimentoDto{
    private Long id;
    private Long pacienteId;
    private String historico;
    private String receituario;
    private String exames;
}