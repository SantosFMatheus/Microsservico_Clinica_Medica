package br.edu.imepac.services;

import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;

    public List<EspecialidadeModel> findAll() {
        return repository.findAll();
    }

    public EspecialidadeModel findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EspecialidadeModel save(EspecialidadeModel especialidade) {
        return repository.save(especialidade);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
