package br.edu.imepac.controllers;


import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) {
        PacienteDto createdPaciente = pacienteService.save(pacienteCreateRequest);
        return new ResponseEntity<>(createdPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPaciente(@PathVariable Long id) {
        PacienteDto pacienteDto = pacienteService.findById(id);
        if (pacienteDto != null) {
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAllPacientes() {
        List<PacienteDto> pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Long id, @RequestBody PacienteDto pacienteDetails) {
        PacienteDto updatedPaciente = pacienteService.update(id, pacienteDetails);
        if (updatedPaciente != null) {
            return new ResponseEntity<>(updatedPaciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}