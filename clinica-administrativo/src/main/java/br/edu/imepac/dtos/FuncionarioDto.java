package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class FuncionarioDto {
    private Long id;
    private String nome;
    private String rg;
    private String orgaoEmissor;
    private String cpf;
    private String endereco;
    private String numeroEndereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String numeroCtps;
    private String numeroPis;
    private String dataNascimento;
}
