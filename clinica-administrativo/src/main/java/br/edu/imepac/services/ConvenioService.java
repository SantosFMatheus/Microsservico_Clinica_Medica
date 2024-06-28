package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    private ConvenioRepository convenioRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ConvenioService(ConvenioRepository convenioRepository, ModelMapper modelMapper) {
        this.convenioRepository = convenioRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        convenioRepository.deleteById(id);
    }

    public List<ConvenioDto> findAll() {
        List<ConvenioModel> convenios = convenioRepository.findAll();
        return convenios.stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDto.class))
                .collect(Collectors.toList());
    }

    public ConvenioDto update(Long id, ConvenioDto convenioDetails) {
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);

        if (optionalConvenio.isPresent()) {
            ConvenioModel convenioModel = optionalConvenio.get();
            convenioModel.setEmpresaConvenio(convenioDetails.getEmpresaConvenio());
            convenioModel.setCnpj(convenioDetails.getCnpj());
            convenioModel.setTelefone(convenioDetails.getTelefone());

            ConvenioModel updatedConvenio = convenioRepository.save(convenioModel);
            return modelMapper.map(updatedConvenio, ConvenioDto.class);
        } else {
            return null;
        }
    }

    public ConvenioDto save(ConvenioCreateRequest convenioRequest) {
        ConvenioModel convenioModel = modelMapper.map(convenioRequest, ConvenioModel.class);
        ConvenioModel savedConvenio = convenioRepository.save(convenioModel);
        return modelMapper.map(savedConvenio, ConvenioDto.class);
    }

    public ConvenioDto findById(Long id) {
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);
        return optionalConvenio.map(convenio -> modelMapper.map(convenio, ConvenioDto.class)).orElse(null);
    }
}
