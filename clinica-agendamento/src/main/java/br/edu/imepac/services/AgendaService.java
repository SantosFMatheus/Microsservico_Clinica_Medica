package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaCreateRequest;
import br.edu.imepac.dtos.AgendaDto;
import br.edu.imepac.models.AgendaModel;
import br.edu.imepac.repositories.AgendaRepository;
import br.edu.imepac.repositories.MedicoRepository;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<AgendaDto> findAll() {
        List<AgendaModel> agendas = agendaRepository.findAll();
        return agendas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AgendaDto findById(Long id) {
        AgendaModel agendaModel = agendaRepository.findById(id).orElse(null);
        if (agendaModel != null) {
            return convertToDto(agendaModel);
        }
        return null;
    }

    public AgendaDto save(AgendaCreateRequest agendaRequest) {
        AgendaModel agendaModel = new AgendaModel();
        agendaModel.setMedicoId(agendaRequest.getMedicoId());
        agendaModel.setPacienteId(agendaRequest.getPacienteId());
        agendaModel.setAtendenteId(agendaRequest.getAtendenteId());
        agendaModel.setData(agendaRequest.getData());
        agendaModel.setHora(agendaRequest.getHora());

        AgendaModel savedAgenda = agendaRepository.save(agendaModel);
        return convertToDto(savedAgenda);
    }

    public AgendaDto update(Long id, AgendaDto agendaDto) {
        AgendaModel agendaModel = agendaRepository.findById(id).orElse(null);
        if (agendaModel != null) {
            agendaModel.setMedicoId(agendaDto.getMedicoId());
            agendaModel.setPacienteId(agendaDto.getPacienteId());
            agendaModel.setAtendenteId(agendaDto.getAtendenteId());
            agendaModel.setData(agendaDto.getData());
            agendaModel.setHora(agendaDto.getHora());

            AgendaModel updatedAgenda = agendaRepository.save(agendaModel);
            return convertToDto(updatedAgenda);
        }
        return null;
    }

    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }

    private AgendaDto convertToDto(AgendaModel agendaModel) {
        AgendaDto agendaDto = new AgendaDto();
        agendaDto.setId(agendaModel.getId());
        agendaDto.setMedicoId(agendaModel.getMedicoId());
        agendaDto.setPacienteId(agendaModel.getPacienteId());
        agendaDto.setAtendenteId(agendaModel.getAtendenteId());
        agendaDto.setData(agendaModel.getData());
        agendaDto.setHora(agendaModel.getHora());

        // Buscar nome do médico pelo ID
        if (agendaModel.getMedicoId() != null) {
            agendaDto.setMedicoNome(medicoRepository.findById(agendaModel.getMedicoId())
                    .map(medico -> medico.getNome())
                    .orElse(null));
        }

        // Buscar nome do paciente pelo ID
        if (agendaModel.getPacienteId() != null) {
            agendaDto.setPacienteNome(pacienteRepository.findById(agendaModel.getPacienteId())
                    .map(paciente -> paciente.getNome())
                    .orElse(null));
        }

        return agendaDto;
    }
}
