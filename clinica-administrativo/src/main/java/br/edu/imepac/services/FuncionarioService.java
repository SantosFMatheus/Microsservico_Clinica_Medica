package br.edu.imepac.services;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, ModelMapper modelMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioDto> findAll() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(funcionario -> modelMapper.map(funcionario, FuncionarioDto.class))
                .collect(Collectors.toList());
    }

    public FuncionarioDto update(Long id, FuncionarioDto funcionarioDetails) {
        Optional<FuncionarioModel> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isPresent()) {
            FuncionarioModel funcionarioModel = optionalFuncionario.get();
            funcionarioModel.setNome(funcionarioDetails.getNome());
            funcionarioModel.setRg(funcionarioDetails.getRg());
            funcionarioModel.setOrgaoEmissor(funcionarioDetails.getOrgaoEmissor());
            funcionarioModel.setCpf(funcionarioDetails.getCpf());
            funcionarioModel.setEndereco(funcionarioDetails.getEndereco());
            funcionarioModel.setNumeroEndereco(funcionarioDetails.getNumeroEndereco());
            funcionarioModel.setComplemento(funcionarioDetails.getComplemento());
            funcionarioModel.setBairro(funcionarioDetails.getBairro());
            funcionarioModel.setCidade(funcionarioDetails.getCidade());
            funcionarioModel.setEstado(funcionarioDetails.getEstado());
            funcionarioModel.setTelefone(funcionarioDetails.getTelefone());
            funcionarioModel.setCelular(funcionarioDetails.getCelular());
            funcionarioModel.setNumeroCtps(funcionarioDetails.getNumeroCtps());
            funcionarioModel.setNumeroPis(funcionarioDetails.getNumeroPis());
            funcionarioModel.setDataNascimento(funcionarioDetails.getDataNascimento());

            FuncionarioModel updatedFuncionario = funcionarioRepository.save(funcionarioModel);
            return modelMapper.map(updatedFuncionario, FuncionarioDto.class);
        } else {
            return null;
        }
    }

    public FuncionarioDto save(FuncionarioCreateRequest funcionarioRequest) {
        FuncionarioModel funcionarioModel = modelMapper.map(funcionarioRequest, FuncionarioModel.class);
        FuncionarioModel savedFuncionario = funcionarioRepository.save(funcionarioModel);
        return modelMapper.map(savedFuncionario, FuncionarioDto.class);
    }

    public FuncionarioDto findById(Long id) {
        Optional<FuncionarioModel> optionalFuncionario = funcionarioRepository.findById(id);
        return optionalFuncionario.map(funcionario -> modelMapper.map(funcionario, FuncionarioDto.class)).orElse(null);
    }
}
