package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "funcionarios")
@Data
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String rg;

    @Column(name = "orgao_emissor")
    private String orgaoEmissor;

    private String cpf;

    private String endereco;

    @Column(name = "numero_endereco")
    private String numeroEndereco;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String telefone;

    private String celular;

    @Column(name = "numero_ctps")
    private String numeroCtps;

    @Column(name = "numero_pis")
    private String numeroPis;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    // Getters and setters são gerados automaticamente pelo Lombok
}
