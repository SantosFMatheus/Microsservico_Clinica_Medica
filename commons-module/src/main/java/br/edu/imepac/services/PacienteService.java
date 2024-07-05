package br.edu.imepac.services;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDto save(PacienteCreateRequest pacienteRequest) {
        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setNome(pacienteRequest.getNome());
        pacienteModel.setRg(pacienteRequest.getRg());
        pacienteModel.setOrgaoEmissor(pacienteRequest.getOrgaoEmissor());
        pacienteModel.setCpf(pacienteRequest.getCpf());
        pacienteModel.setEndereco(pacienteRequest.getEndereco());
        pacienteModel.setNumero(pacienteRequest.getNumero());
        pacienteModel.setComplemento(pacienteRequest.getComplemento());
        pacienteModel.setBairro(pacienteRequest.getBairro());
        pacienteModel.setCidade(pacienteRequest.getCidade());
        pacienteModel.setEstado(pacienteRequest.getEstado());
        pacienteModel.setTelefone(pacienteRequest.getTelefone());
        pacienteModel.setCelular(pacienteRequest.getCelular());
        pacienteModel.setDataNascimento(pacienteRequest.getDataNascimento());
        pacienteModel.setSexo(pacienteRequest.getSexo());
        pacienteModel.setPossuiConvenio(pacienteRequest.isPossuiConvenio());
        pacienteModel.setNomeConvenio(pacienteRequest.getNomeConvenio());

        PacienteModel savedPaciente = pacienteRepository.save(pacienteModel);
        return convertToDto(savedPaciente);
    }

    public PacienteDto findById(Long id) {
        Optional<PacienteModel> optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.map(this::convertToDto).orElse(null);
    }

    public List<PacienteDto> findAll() {
        List<PacienteModel> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }

    public PacienteDto update(Long id, PacienteDto pacienteDetails) {
        Optional<PacienteModel> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            PacienteModel pacienteModel = optionalPaciente.get();
            pacienteModel.setNome(pacienteDetails.getNome());
            pacienteModel.setRg(pacienteDetails.getRg());
            pacienteModel.setOrgaoEmissor(pacienteDetails.getOrgaoEmissor());
            pacienteModel.setCpf(pacienteDetails.getCpf());
            pacienteModel.setEndereco(pacienteDetails.getEndereco());
            pacienteModel.setNumero(pacienteDetails.getNumero());
            pacienteModel.setComplemento(pacienteDetails.getComplemento());
            pacienteModel.setBairro(pacienteDetails.getBairro());
            pacienteModel.setCidade(pacienteDetails.getCidade());
            pacienteModel.setEstado(pacienteDetails.getEstado());
            pacienteModel.setTelefone(pacienteDetails.getTelefone());
            pacienteModel.setCelular(pacienteDetails.getCelular());
            pacienteModel.setDataNascimento(pacienteDetails.getDataNascimento());
            pacienteModel.setSexo(pacienteDetails.getSexo());
            pacienteModel.setPossuiConvenio(pacienteDetails.isPossuiConvenio());
            pacienteModel.setNomeConvenio(pacienteDetails.getNomeConvenio());

            PacienteModel updatedPaciente = pacienteRepository.save(pacienteModel);
            return convertToDto(updatedPaciente);
        } else {
            return null;
        }
    }

    private PacienteDto convertToDto(PacienteModel pacienteModel) {
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setId(pacienteModel.getId());
        pacienteDto.setNome(pacienteModel.getNome());
        pacienteDto.setRg(pacienteModel.getRg());
        pacienteDto.setOrgaoEmissor(pacienteModel.getOrgaoEmissor());
        pacienteDto.setCpf(pacienteModel.getCpf());
        pacienteDto.setEndereco(pacienteModel.getEndereco());
        pacienteDto.setNumero(pacienteModel.getNumero());
        pacienteDto.setComplemento(pacienteModel.getComplemento());
        pacienteDto.setBairro(pacienteModel.getBairro());
        pacienteDto.setCidade(pacienteModel.getCidade());
        pacienteDto.setEstado(pacienteModel.getEstado());
        pacienteDto.setTelefone(pacienteModel.getTelefone());
        pacienteDto.setCelular(pacienteModel.getCelular());
        pacienteDto.setDataNascimento(pacienteModel.getDataNascimento());
        pacienteDto.setSexo(pacienteModel.getSexo());
        pacienteDto.setPossuiConvenio(pacienteModel.isPossuiConvenio());
        pacienteDto.setNomeConvenio(pacienteModel.getNomeConvenio());
        return pacienteDto;
    }
}