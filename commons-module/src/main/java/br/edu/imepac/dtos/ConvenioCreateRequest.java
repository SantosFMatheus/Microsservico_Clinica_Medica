package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioCreateRequest {
    private String empresaConvenio;
    private String cnpj;
    private String telefone;
}
