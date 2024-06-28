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

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDto> savePaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) {
        PacienteDto savedPaciente = pacienteService.save(pacienteCreateRequest);
        return new ResponseEntity<>(savedPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> listAllPacientes() {
        List<PacienteDto> pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> getPacienteById(@PathVariable Long id) {
        PacienteDto pacienteDto = pacienteService.findById(id);
        if (pacienteDto != null) {
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
