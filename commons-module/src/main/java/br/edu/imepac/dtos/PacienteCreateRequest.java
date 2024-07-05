package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PacienteCreateRequest {
    private String nome;
    private String rg;
    private String orgaoEmissor;
    private String cpf;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String dataNascimento;
    private String sexo;
    private boolean possuiConvenio;
    private String nomeConvenio;
}

