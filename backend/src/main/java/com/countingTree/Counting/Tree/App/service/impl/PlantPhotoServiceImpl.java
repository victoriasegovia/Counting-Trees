package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.countingTree.Counting.Tree.App.model.PlantPhoto;
import com.countingTree.Counting.Tree.App.repository.PlantPhotoRepository;
import com.countingTree.Counting.Tree.App.service.PlantPhotoService;

public class PlantPhotoServiceImpl implements PlantPhotoService {

    @Autowired
    private PlantPhotoRepository plantPhotoRepository;

    @Override
    public PlantPhoto getPlantPhotoById(Long plantId) {
        PlantPhoto photoSearched = plantPhotoRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Photo for Plant with ID " + plantId + " not found."));
        return photoSearched;
    }

    @Override
    public List<PlantPhoto> getAllPlantPhotos() {
        return plantPhotoRepository.findAll();
    }

    @Override
    public List<PlantPhoto> getAllPlantPhotosForPlant(Long plantId) {
        return plantPhotoRepository.findAll().stream()
                .filter(photo -> photo.getPlant() != null && photo.getPlant().getPlantId().equals(plantId))
                .toList();
    }

    @Override
    public void updatePlantPhoto(Long photoId, PlantPhoto newPhoto) {
        PlantPhoto existingPhoto = plantPhotoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo for Plant with ID " + photoId + " not found."));

        existingPhoto.setUrl(newPhoto.getUrl());
        existingPhoto.setDateTaken(newPhoto.getDateTaken());

        plantPhotoRepository.save(existingPhoto);
    }

    @Override
    public void deletePlantPhoto(Long photoId) {
        plantPhotoRepository.deleteById(photoId);
    }

    @Override
    public void addPlantPhoto(Long plantId, PlantPhoto photo) {
        validatePhoto(photo);
        plantPhotoRepository.save(photo);
    }

    // EXTRA METHODS
    private void validatePhoto(PlantPhoto newPhoto) {
        if (newPhoto.getUrl() == null || newPhoto.getUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("Photo URL cannot be null or empty");
        }
        if (newPhoto.getDateTaken() == null) {
            throw new IllegalArgumentException("Photo date cannot be null");
        }
        if (newPhoto.getPlant() == null) {
            throw new IllegalArgumentException("Photo must be associated with a plant");
        }
    }

}
