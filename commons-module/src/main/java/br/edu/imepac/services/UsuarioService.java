package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDto> findAll() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    public UsuarioDto update(Long id, UsuarioDto usuarioDetails) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            UsuarioModel usuarioModel = optionalUsuario.get();
            usuarioModel.setIdentificacaoUsuario(usuarioDetails.getIdentificacaoUsuario());
            // Outros campos podem ser atualizados conforme necessário

            UsuarioModel updatedUsuario = usuarioRepository.save(usuarioModel);
            return modelMapper.map(updatedUsuario, UsuarioDto.class);
        } else {
            return null;
        }
    }

    public UsuarioDto save(UsuarioCreateRequest usuarioRequest) {
        UsuarioModel usuarioModel = modelMapper.map(usuarioRequest, UsuarioModel.class);
        UsuarioModel savedUsuario = usuarioRepository.save(usuarioModel);
        return modelMapper.map(savedUsuario, UsuarioDto.class);
    }

    public UsuarioDto findById(Long id) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.map(usuario -> modelMapper.map(usuario, UsuarioDto.class)).orElse(null);
    }
}
