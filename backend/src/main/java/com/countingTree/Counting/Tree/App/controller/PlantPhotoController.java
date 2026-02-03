package com.countingTree.Counting.Tree.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.countingTree.Counting.Tree.App.model.PlantPhoto;
import com.countingTree.Counting.Tree.App.service.PlantPhotoService;

@RestController
@RequestMapping("/api/v1/plant-photos")
public class PlantPhotoController {

    @Autowired
    private PlantPhotoService plantPhotoService;

    @GetMapping("/{id}")
    public ResponseEntity<PlantPhoto> getPlantPhotoById(@PathVariable("id") Long photoId) {
        return ResponseEntity.ok(plantPhotoService.getPlantPhotoById(photoId));
    }

    @PostMapping("/{plantId}")
    public ResponseEntity<Void> addPlantPhoto(@PathVariable("plantId") Long plantId, @RequestBody PlantPhoto plantPhoto) {
        plantPhotoService.addPlantPhoto(plantId, plantPhoto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlantPhoto(@PathVariable("id") Long photoId, @RequestBody PlantPhoto plantPhoto) {
        plantPhotoService.updatePlantPhoto(photoId, plantPhoto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlantPhoto(@PathVariable("id") Long id) {
        plantPhotoService.deletePlantPhoto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PlantPhoto>> getAllPlantPhotos() {
        return ResponseEntity.ok(plantPhotoService.getAllPlantPhotos());
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<PlantPhoto>> getPhotosForPlant(@PathVariable("plantId") Long plantId) {
        return ResponseEntity.ok(plantPhotoService.getAllPlantPhotosForPlant(plantId));
    }
}
