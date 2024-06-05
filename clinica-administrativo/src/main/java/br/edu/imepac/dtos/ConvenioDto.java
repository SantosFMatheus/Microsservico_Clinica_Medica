package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioDto {
    private Long id;
    private String nome;
    private String status;
    private String cobertura;
}
