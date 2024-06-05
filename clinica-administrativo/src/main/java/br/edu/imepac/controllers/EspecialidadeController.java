package br.edu.imepac.controllers;

import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public List<EspecialidadeModel> getAllEspecialidades() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EspecialidadeModel getEspecialidadeById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public EspecialidadeModel createEspecialidade(@RequestBody EspecialidadeModel especialidade) {
        return service.save(especialidade);
    }

    @PutMapping("/{id}")
    public EspecialidadeModel updateEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeModel especialidade) {
        EspecialidadeModel existing = service.findById(id);
        if (existing != null) {
            especialidade.setId(id);
            return service.save(especialidade);
        }
        return null; // Ou lance uma exceção se preferir
    }

    @DeleteMapping("/{id}")
    public void deleteEspecialidade(@PathVariable Long id) {
        service.deleteById(id);
    }
}
