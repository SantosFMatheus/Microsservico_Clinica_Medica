package br.edu.imepac.controllers;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping
    public ResponseEntity<EspecialidadeDto> createEspecialidade(@RequestBody EspecialidadeCreateRequest especialidadeCreateRequest) {
        EspecialidadeDto createdEspecialidade = especialidadeService.save(especialidadeCreateRequest);
        return new ResponseEntity<>(createdEspecialidade, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDto> getEspecialidade(@PathVariable Long id) {
        EspecialidadeDto especialidadeDto = especialidadeService.findById(id);
        if (especialidadeDto != null) {
            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeDto>> getAllEspecialidades() {
        List<EspecialidadeDto> especialidades = especialidadeService.findAll();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable Long id) {
        especialidadeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeDto> updateEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeDto especialidadeDetails) {
        EspecialidadeDto updatedEspecialidade = especialidadeService.update(id, especialidadeDetails);
        if (updatedEspecialidade != null) {
            return new ResponseEntity<>(updatedEspecialidade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
