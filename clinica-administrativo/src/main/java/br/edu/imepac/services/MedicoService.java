package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import br.edu.imepac.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public void delete(Long id) {
        medicoRepository.deleteById(id);
    }

    public List<MedicoDto> findAll() {
        List<MedicoModel> medicos = medicoRepository.findAll();
        return medicos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public MedicoDto update(Long id, MedicoDto medicoDetails) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            MedicoModel medicoModel = optionalMedico.get();
            medicoModel.setNome(medicoDetails.getNome());
            medicoModel.setCrm(medicoDetails.getCrm());
            medicoModel.setSenha(medicoDetails.getSenha());

            Set<EspecialidadeModel> especialidades = especialidadeRepository.findAllById(medicoDetails.getEspecialidadeIds()).stream().collect(Collectors.toSet());
            medicoModel.setEspecialidades(especialidades);

            MedicoModel updatedMedico = medicoRepository.save(medicoModel);

            return convertToDto(updatedMedico);
        } else {
            return null;
        }
    }

    public MedicoDto save(MedicoCreateRequest medicoRequest) {
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setNome(medicoRequest.getNome());
        medicoModel.setCrm(medicoRequest.getCrm());
        medicoModel.setSenha(medicoRequest.getSenha());

        Set<EspecialidadeModel> especialidades = especialidadeRepository.findAllById(medicoRequest.getEspecialidadeIds()).stream().collect(Collectors.toSet());
        medicoModel.setEspecialidades(especialidades);

        MedicoModel savedMedico = medicoRepository.save(medicoModel);

        return convertToDto(savedMedico);
    }

    public MedicoDto findById(Long id) {
        Optional<MedicoModel> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            return convertToDto(optionalMedico.get());
        } else {
            return null;
        }
    }

    private MedicoDto convertToDto(MedicoModel medicoModel) {
        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setId(medicoModel.getId());
        medicoDto.setNome(medicoModel.getNome());
        medicoDto.setCrm(medicoModel.getCrm());
        medicoDto.setSenha(medicoModel.getSenha());

        Set<Long> especialidadeIds = medicoModel.getEspecialidades().stream().map(EspecialidadeModel::getId).collect(Collectors.toSet());
        medicoDto.setEspecialidadeIds(especialidadeIds);

        return medicoDto;
    }
}
