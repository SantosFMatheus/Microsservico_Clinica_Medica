package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioCreateRequest {
    private String nome;
    private String status;
    private String cobertura;
}
