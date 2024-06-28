package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String identificacaoUsuario;
    // Outros campos que podem ser necessários para DTOs de usuários
}
