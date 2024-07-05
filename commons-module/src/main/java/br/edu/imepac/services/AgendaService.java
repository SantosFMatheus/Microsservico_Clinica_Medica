package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaCreateRequest;
import br.edu.imepac.dtos.AgendaDto;
import br.edu.imepac.models.AgendaModel;
import br.edu.imepac.repositories.AgendaRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    public List<AgendaDto> getAllAgendas() {
        return agendaRepository.findAll().stream()
                .map(agenda -> modelMapper.map(agenda, AgendaDto.class))
                .collect(Collectors.toList());
    }

    public Optional<AgendaDto> getAgendaById(Long id) {
        return agendaRepository.findById(id)
                .map(agenda -> modelMapper.map(agenda, AgendaDto.class));
    }

    public AgendaDto createAgenda(AgendaCreateRequest agendaRequest) {
        AgendaModel agenda = modelMapper.map(agendaRequest, AgendaModel.class);
        agenda = agendaRepository.save(agenda);
        return modelMapper.map(agenda, AgendaDto.class);
    }

    public Optional<AgendaDto> updateAgenda(Long id, AgendaDto agendaDto) {
        return agendaRepository.findById(id)
                .map(existingAgenda -> {
                    modelMapper.map(agendaDto, existingAgenda);
                    agendaRepository.save(existingAgenda);
                    return modelMapper.map(existingAgenda, AgendaDto.class);
                });
    }

    public void deleteAgenda(Long id) {
        agendaRepository.deleteById(id);
    }
}