package br.edu.imepac.services;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.AtendimentoModel;
import br.edu.imepac.repositories.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    // Save method
    public AtendimentoDto save(AtendimentoCreateRequest atendimentoRequest) {
        AtendimentoModel atendimento = new AtendimentoModel();
        atendimento.setPacienteId(atendimentoRequest.getPacienteId());
        atendimento.setHistorico(atendimentoRequest.getHistorico());
        atendimento.setReceituario(atendimentoRequest.getReceituario());
        atendimento.setExames(atendimentoRequest.getExames());

        AtendimentoModel savedAtendimento = atendimentoRepository.save(atendimento);

        AtendimentoDto result = new AtendimentoDto();
        result.setId(savedAtendimento.getId());
        result.setPacienteId(savedAtendimento.getPacienteId());
        result.setHistorico(savedAtendimento.getHistorico());
        result.setReceituario(savedAtendimento.getReceituario());
        result.setExames(savedAtendimento.getExames());

        return result;
    }

    // Find all method
    public List<AtendimentoDto> findAll() {
        List<AtendimentoModel> atendimentos = atendimentoRepository.findAll();
        return atendimentos.stream().map(atendimento -> {
            AtendimentoDto dto = new AtendimentoDto();
            dto.setId(atendimento.getId());
            dto.setPacienteId(atendimento.getPacienteId());
            dto.setHistorico(atendimento.getHistorico());
            dto.setReceituario(atendimento.getReceituario());
            dto.setExames(atendimento.getExames());
            return dto;
        }).collect(Collectors.toList());
    }

    // Find by ID method
    public AtendimentoDto findById(Long id) {
        Optional<AtendimentoModel> optionalAtendimento = atendimentoRepository.findById(id);
        if (optionalAtendimento.isPresent()) {
            AtendimentoModel atendimento = optionalAtendimento.get();
            AtendimentoDto dto = new AtendimentoDto();
            dto.setId(atendimento.getId());
            dto.setPacienteId(atendimento.getPacienteId());
            dto.setHistorico(atendimento.getHistorico());
            dto.setReceituario(atendimento.getReceituario());
            dto.setExames(atendimento.getExames());
            return dto;
        } else {
            return null;
        }
    }

    // Update method
    public AtendimentoDto update(Long id, AtendimentoDto atendimentoDto) {
        Optional<AtendimentoModel> optionalAtendimento = atendimentoRepository.findById(id);

        if (optionalAtendimento.isPresent()) {
            AtendimentoModel atendimento = optionalAtendimento.get();
            atendimento.setHistorico(atendimentoDto.getHistorico());
            atendimento.setReceituario(atendimentoDto.getReceituario());
            atendimento.setExames(atendimentoDto.getExames());

            AtendimentoModel updatedAtendimento = atendimentoRepository.save(atendimento);

            AtendimentoDto result = new AtendimentoDto();
            result.setId(updatedAtendimento.getId());
            result.setPacienteId(updatedAtendimento.getPacienteId());
            result.setHistorico(updatedAtendimento.getHistorico());
            result.setReceituario(updatedAtendimento.getReceituario());
            result.setExames(updatedAtendimento.getExames());

            return result;
        } else {
            return null;
        }
    }

    // Delete method
    public void delete(Long id) {
        atendimentoRepository.deleteById(id);
    }
}
