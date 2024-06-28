package br.edu.imepac.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class MedicoDto {
    private Long id;
    private String nome;
    private String crm;
    private String senha;
    private Set<Long> especialidadeIds; // Adicione esta linha
}
