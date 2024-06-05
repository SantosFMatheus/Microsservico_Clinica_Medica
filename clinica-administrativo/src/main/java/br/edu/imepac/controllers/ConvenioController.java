package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.services.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService service;

    @GetMapping
    public List<ConvenioDto> getAllConvenios() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ConvenioDto getConvenioById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ConvenioDto createConvenio(@RequestBody ConvenioCreateRequest convenioRequest) {
        return service.save(convenioRequest);
    }

    @PutMapping("/{id}")
    public ConvenioDto updateConvenio(@PathVariable Long id, @RequestBody ConvenioDto convenioDto) {
        return service.update(id, convenioDto);
    }

    @DeleteMapping("/{id}")
    public void deleteConvenio(@PathVariable Long id) {
        service.deleteById(id);
    }
}
