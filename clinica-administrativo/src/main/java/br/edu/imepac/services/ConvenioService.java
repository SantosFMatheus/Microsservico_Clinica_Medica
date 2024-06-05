package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository repository;

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ConvenioDto> findAll() {
        List<ConvenioModel> convenios = repository.findAll();
        return convenios.stream().map(convenio -> {
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenio.getId());
            convenioDto.setNome(convenio.getNome());
            convenioDto.setStatus(convenio.getStatus());
            convenioDto.setCobertura(convenio.getCobertura());
            return convenioDto;
        }).collect(Collectors.toList());
    }

    public ConvenioDto findById(Long id) {
        Optional<ConvenioModel> optionalConvenio = repository.findById(id);
        if (optionalConvenio.isPresent()) {
            ConvenioModel convenio = optionalConvenio.get();
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenio.getId());
            convenioDto.setNome(convenio.getNome());
            convenioDto.setStatus(convenio.getStatus());
            convenioDto.setCobertura(convenio.getCobertura());
            return convenioDto;
        } else {
            return null;
        }
    }

    public ConvenioDto save(ConvenioCreateRequest convenioRequest) {
        ConvenioModel convenio = new ConvenioModel();
        convenio.setNome(convenioRequest.getNome());
        convenio.setStatus(convenioRequest.getStatus());
        convenio.setCobertura(convenioRequest.getCobertura());

        ConvenioModel savedConvenio = repository.save(convenio);

        ConvenioDto convenioDto = new ConvenioDto();
        convenioDto.setId(savedConvenio.getId());
        convenioDto.setNome(savedConvenio.getNome());
        convenioDto.setStatus(savedConvenio.getStatus());
        convenioDto.setCobertura(savedConvenio.getCobertura());

        return convenioDto;
    }

    public ConvenioDto update(Long id, ConvenioDto convenioDto) {
        Optional<ConvenioModel> optionalConvenio = repository.findById(id);
        if (optionalConvenio.isPresent()) {
            ConvenioModel convenio = optionalConvenio.get();
            convenio.setNome(convenioDto.getNome());
            convenio.setStatus(convenioDto.getStatus());
            convenio.setCobertura(convenioDto.getCobertura());

            ConvenioModel updatedConvenio = repository.save(convenio);

            ConvenioDto updatedDto = new ConvenioDto();
            updatedDto.setId(updatedConvenio.getId());
            updatedDto.setNome(updatedConvenio.getNome());
            updatedDto.setStatus(updatedConvenio.getStatus());
            updatedDto.setCobertura(updatedConvenio.getCobertura());

            return updatedDto;
        } else {
            return null;
        }
    }
}
