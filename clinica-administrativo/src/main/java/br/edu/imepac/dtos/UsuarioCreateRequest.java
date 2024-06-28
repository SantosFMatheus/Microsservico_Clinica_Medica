package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class UsuarioCreateRequest {
    private String identificacaoUsuario;
    private String senhaAcesso;
}
