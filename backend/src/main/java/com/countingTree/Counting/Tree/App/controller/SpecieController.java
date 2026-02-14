package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.SpecieDTO;
import com.countingTree.Counting.Tree.App.model.Specie;
import com.countingTree.Counting.Tree.App.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/species")
public class SpecieController {

    @Autowired
    private SpecieService specieService;

    @GetMapping("/{id}")
    public ResponseEntity<SpecieDTO> getSpecieById(@PathVariable Long id) {
        return ResponseEntity.ok(specieService.getSpecieById(id));
    }

    @GetMapping
    public ResponseEntity<List<SpecieDTO>> getAllSpecies() {
        return ResponseEntity.ok(specieService.getAllSpecies());
    }

    @PostMapping
    public ResponseEntity<Void> addSpecie(@RequestBody Specie specie) {
        specieService.addSpecie(specie);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecieDTO> updateSpecie(@PathVariable Long id, @RequestBody Specie specie) {
        SpecieDTO updated = specieService.updateSpecie(id, specie);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecie(@PathVariable Long id) {
        specieService.deleteSpecie(id);
        return ResponseEntity.ok().build();
    }

}
