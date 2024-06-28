package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaCreateRequest;
import br.edu.imepac.dtos.AgendaDto;
import br.edu.imepac.models.AgendaModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.AgendaRepository;
import br.edu.imepac.repositories.MedicoRepository;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public AgendaDto save(AgendaCreateRequest agendaRequest) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(agendaRequest.getMedicoId());
        if (!optionalMedico.isPresent()) {
            throw new RuntimeException("Medico not found");
        }

        Optional<PacienteModel> optionalPaciente = pacienteRepository.findById(agendaRequest.getPacienteId());
        if (!optionalPaciente.isPresent()) {
            throw new RuntimeException("Paciente not found");
        }

        AgendaModel agendaModel = new AgendaModel();
        agendaModel.setMedico(optionalMedico.get());
        agendaModel.setPaciente(optionalPaciente.get());
        agendaModel.setAtendenteId(agendaRequest.getAtendenteId());
        agendaModel.setData(agendaRequest.getData());
        agendaModel.setHora(agendaRequest.getHora());

        AgendaModel savedAgenda = agendaRepository.save(agendaModel);

        return convertToDto(savedAgenda);
    }

    public AgendaDto findById(Long id) {
        Optional<AgendaModel> optionalAgenda = agendaRepository.findById(id);
        if (optionalAgenda.isPresent()) {
            return convertToDto(optionalAgenda.get());
        } else {
            return null;
        }
    }

    public List<AgendaDto> findAll() {
        List<AgendaModel> agendas = agendaRepository.findAll();
        return agendas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }

    private AgendaDto convertToDto(AgendaModel agendaModel) {
        AgendaDto agendaDto = new AgendaDto();
        agendaDto.setId(agendaModel.getId());
        agendaDto.setMedicoId(agendaModel.getMedico().getId());
        agendaDto.setMedicoNome(agendaModel.getMedico().getNome());
        agendaDto.setPacienteId(agendaModel.getPaciente().getId());
        agendaDto.setPacienteNome(agendaModel.getPaciente().getNome());
        agendaDto.setAtendenteId(agendaModel.getAtendenteId());
        agendaDto.setData(agendaModel.getData());
        agendaDto.setHora(agendaModel.getHora());
        return agendaDto;
    }
}
