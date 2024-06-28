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
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaDto> createAgenda(@RequestBody AgendaCreateRequest agendaCreateRequest) {
        AgendaDto createdAgenda = agendaService.save(agendaCreateRequest);
        return new ResponseEntity<>(createdAgenda, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> getAgenda(@PathVariable Long id) {
        AgendaDto agendaDto = agendaService.findById(id);
        if (agendaDto != null) {
            return new ResponseEntity<>(agendaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AgendaDto>> getAllAgendas() {
        List<AgendaDto> agendas = agendaService.findAll();
        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        agendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
