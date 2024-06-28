package br.edu.imepac.dtos;

import br.edu.imepac.models.PacienteModel;
import lombok.Data;

@Data
public class AtendimentoDto {

    private Long id;
    private PacienteModel paciente;
    private String historico;
    private String receituario;
    private String exames;
}
