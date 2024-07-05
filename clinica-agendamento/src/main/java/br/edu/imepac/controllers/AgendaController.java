package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AgendaCreateRequest;
import br.edu.imepac.dtos.AgendaDto;
import br.edu.imepac.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaDto>> findAll() {
        List<AgendaDto> agendas = agendaService.getAllAgendas();
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> findById(@PathVariable Long id) {
        Optional<AgendaDto> agendaDto = agendaService.getAgendaById(id);
        return agendaDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AgendaDto> create(@RequestBody AgendaCreateRequest agendaRequest) {
        AgendaDto createdAgenda = agendaService.createAgenda(agendaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDto> update(@PathVariable Long id, @RequestBody AgendaDto agendaDto) {
        Optional<AgendaDto> updatedAgenda = agendaService.updateAgenda(id, agendaDto);
        return updatedAgenda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }
}