package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.PlantDTO;
import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/{id}")
    public ResponseEntity<PlantDTO> getPlant(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlantDTO>> getAllPlants() {
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @PostMapping
    public ResponseEntity<Void> addPlant(@RequestBody Plant plant) {
        plantService.addPlant(plant);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantDTO> updatePlant(@PathVariable Long id, @RequestBody Plant plant) {
        PlantDTO updated = plantService.updatePlant(id, plant);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.ok().build();
    }

}
