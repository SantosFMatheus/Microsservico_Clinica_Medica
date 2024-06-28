package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public void delete(Long id) {
        especialidadeRepository.deleteById(id);
    }

    public List<EspecialidadeDto> findAll() {
        List<EspecialidadeModel> especialidades = especialidadeRepository.findAll();
        return especialidades.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public EspecialidadeDto update(Long id, EspecialidadeDto especialidadeDetails) {
        Optional<EspecialidadeModel> optionalEspecialidade = especialidadeRepository.findById(id);

        if (optionalEspecialidade.isPresent()) {
            EspecialidadeModel especialidadeModel = optionalEspecialidade.get();
            especialidadeModel.setNome(especialidadeDetails.getNome());

            EspecialidadeModel updatedEspecialidade = especialidadeRepository.save(especialidadeModel);

            return convertToDto(updatedEspecialidade);
        } else {
            return null;
        }
    }

    public EspecialidadeDto save(EspecialidadeCreateRequest especialidadeRequest) {
        EspecialidadeModel especialidadeModel = new EspecialidadeModel();
        especialidadeModel.setNome(especialidadeRequest.getNome());

        EspecialidadeModel savedEspecialidade = especialidadeRepository.save(especialidadeModel);

        return convertToDto(savedEspecialidade);
    }

    public EspecialidadeDto findById(Long id) {
        Optional<EspecialidadeModel> optionalEspecialidade = especialidadeRepository.findById(id);
        if (optionalEspecialidade.isPresent()) {
            return convertToDto(optionalEspecialidade.get());
        } else {
            return null;
        }
    }

    private EspecialidadeDto convertToDto(EspecialidadeModel especialidadeModel) {
        EspecialidadeDto especialidadeDto = new EspecialidadeDto();
        especialidadeDto.setId(especialidadeModel.getId());
        especialidadeDto.setNome(especialidadeModel.getNome());
        return especialidadeDto;
    }
}
