package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.services.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoDto> create(@RequestBody AtendimentoCreateRequest atendimentoRequest) {
        AtendimentoDto createdAtendimento = atendimentoService.save(atendimentoRequest);
        return ResponseEntity.ok(createdAtendimento);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoDto>> getAll() {
        List<AtendimentoDto> atendimentos = atendimentoService.findAll();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDto> getById(@PathVariable Long id) {
        AtendimentoDto atendimento = atendimentoService.findById(id);
        if (atendimento != null) {
            return ResponseEntity.ok(atendimento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDto> update(@PathVariable Long id, @RequestBody AtendimentoDto atendimentoDto) {
        AtendimentoDto updatedAtendimento = atendimentoService.update(id, atendimentoDto);
        if (updatedAtendimento != null) {
            return ResponseEntity.ok(updatedAtendimento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atendimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
