package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AgendaCreateRequest;
import br.edu.imepac.dtos.AgendaDto;
import br.edu.imepac.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaDto>> findAll() {
        List<AgendaDto> agendas = agendaService.findAll();
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> findById(@PathVariable Long id) {
        AgendaDto agendaDto = agendaService.findById(id);
        if (agendaDto != null) {
            return ResponseEntity.ok(agendaDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AgendaDto> create(@RequestBody AgendaCreateRequest agendaRequest) {
        AgendaDto createdAgenda = agendaService.save(agendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDto> update(@PathVariable Long id, @RequestBody AgendaDto agendaDto) {
        AgendaDto updatedAgenda = agendaService.update(id, agendaDto);
        if (updatedAgenda != null) {
            return ResponseEntity.ok(updatedAgenda);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
