package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.model.PlantPhoto;
import com.countingTree.Counting.Tree.App.repository.PlantPhotoRepository;
import com.countingTree.Counting.Tree.App.repository.PlantRepository;
import com.countingTree.Counting.Tree.App.service.PlantPhotoService;

@Service
@Transactional
public class PlantPhotoServiceImpl implements PlantPhotoService {

    @Autowired
    private PlantPhotoRepository plantPhotoRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Override
    public PlantPhoto getPlantPhotoById(Long photoId) {
        PlantPhoto photoSearched = plantPhotoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo with ID " + photoId + " not found."));
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
                .orElseThrow(() -> new IllegalArgumentException("Photo with ID " + photoId + " not found."));

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
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        photo.setPlant(plant);
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
        // photo will be associated with plant in addPlantPhoto, skip null check here
    }

}    
