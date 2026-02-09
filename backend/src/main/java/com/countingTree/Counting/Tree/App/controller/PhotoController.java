package com.countingTree.Counting.Tree.App.controller;

import java.util.List;

import com.countingTree.Counting.Tree.App.dto.PhotoDTO;
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

import com.countingTree.Counting.Tree.App.model.Photo;
import com.countingTree.Counting.Tree.App.service.PhotoService;

@RestController
@RequestMapping("/api/v1/-photos")
public class PhotoController {

    @Autowired
    private PhotoService PhotoService;

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> getPlantPhotoById(@PathVariable("id") Long photoId) {
        return ResponseEntity.ok(PhotoService.getPhotoById(photoId));
    }

    @GetMapping
    public ResponseEntity<List<PhotoDTO>> getAllPhotos() {
        return ResponseEntity.ok(PhotoService.getAllPhotos());
    }

    @GetMapping("/plants/{id}")
    public ResponseEntity<List<PhotoDTO>> getAllPhotosForPlant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(PhotoService.getAllPhotosForPlant(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<PhotoDTO> getPhotoForUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(PhotoService.getPhotoForUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> addPhoto(@RequestBody Photo photo) {
        PhotoService.addPhoto(photo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoDTO> updatePhoto(@PathVariable("id") Long photoId, @RequestBody Photo photo) {
        PhotoDTO updated = PhotoService.updatePhoto(photoId, photo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable("id") Long id) {
        PhotoService.deletePhoto(id);
        return ResponseEntity.ok().build();
    }

}
