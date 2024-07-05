package br.edu.imepac.services;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.AtendimentoModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.AtendimentoRepository;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public AtendimentoDto save(AtendimentoCreateRequest atendimentoRequest) {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        Optional<PacienteModel> paciente = pacienteRepository.findById(atendimentoRequest.getPacienteId());
        if (paciente.isPresent()) {
            atendimentoModel.setPaciente(paciente.get());
            atendimentoModel.setHistorico(atendimentoRequest.getHistorico());
            atendimentoModel.setReceituario(atendimentoRequest.getReceituario());
            atendimentoModel.setExames(atendimentoRequest.getExames());
            AtendimentoModel savedAtendimento = atendimentoRepository.save(atendimentoModel);
            return convertToDto(savedAtendimento);
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

    public AtendimentoDto findById(Long id) {
        Optional<AtendimentoModel> optionalAtendimento = atendimentoRepository.findById(id);
        return optionalAtendimento.map(this::convertToDto).orElse(null);
    }

    public List<AtendimentoDto> findAll() {
        List<AtendimentoModel> atendimentos = atendimentoRepository.findAll();
        return atendimentos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        atendimentoRepository.deleteById(id);
    }

    public AtendimentoDto update(Long id, AtendimentoDto atendimentoDetails) {
        Optional<AtendimentoModel> optionalAtendimento = atendimentoRepository.findById(id);
        if (optionalAtendimento.isPresent()) {
            AtendimentoModel atendimentoModel = optionalAtendimento.get();
            atendimentoModel.setHistorico(atendimentoDetails.getHistorico());
            atendimentoModel.setReceituario(atendimentoDetails.getReceituario());
            atendimentoModel.setExames(atendimentoDetails.getExames());
            AtendimentoModel updatedAtendimento = atendimentoRepository.save(atendimentoModel);
            return convertToDto(updatedAtendimento);
        } else {
            return null;
        }
    }

    private AtendimentoDto convertToDto(AtendimentoModel atendimentoModel) {
        AtendimentoDto atendimentoDto = new AtendimentoDto();
        atendimentoDto.setId(atendimentoModel.getId());
        atendimentoDto.setPacienteId(atendimentoModel.getPaciente().getId());
        atendimentoDto.setHistorico(atendimentoModel.getHistorico());
        atendimentoDto.setReceituario(atendimentoModel.getReceituario());
        atendimentoDto.setExames(atendimentoModel.getExames());
        return atendimentoDto;
    }
}