package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.repository.PlantRepository;
import com.countingTree.Counting.Tree.App.service.PlantService;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Override
    public void addPlant(Plant newPlant) {
        validatePlant(newPlant);
        plantRepository.save(newPlant);
    }

    @Override
    public void updatePlant(Long plantId, Plant newPlant) {
        Plant existingPlant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        validatePlant(newPlant);
        existingPlant.setMainPhoto(newPlant.getMainPhoto());
        existingPlant.setDatePlanted(newPlant.getDatePlanted());
        existingPlant.setSpecies(newPlant.getSpecies());
        existingPlant.setOwner(newPlant.getOwner());
        existingPlant.setPhotos(newPlant.getPhotos());
        existingPlant.setHealthStatus(newPlant.getHealthStatus());
        existingPlant.setVerificationStatus(newPlant.getVerificationStatus());
        existingPlant.setAlerts(newPlant.getAlerts());
        existingPlant.setComments(newPlant.getComments());
        plantRepository.save(existingPlant);
    }

    @Override
    public void deletePlant(Long plantId) {
        plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        plantRepository.deleteById(plantId);
    }

    @Override
    public Plant getPlant(Long plantId) {
        Plant existingPlant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        return existingPlant;
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }


    // EXTRA METHODS
    private void validatePlant(Plant plant) {
        if (plant == null) {
            throw new IllegalArgumentException("Plant must not be null");
        }
        if (plant.getSpecies() == null) {
            throw new IllegalArgumentException("Plant must have a species");
        }
        if (plant.getOwner() == null) {
            throw new IllegalArgumentException("Plant must have an owner");
        }
    }

}
