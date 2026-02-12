package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.countingTree.Counting.Tree.App.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countingTree.Counting.Tree.App.model.Alert;
import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.model.Photo;
import com.countingTree.Counting.Tree.App.dto.PhotoDTO;
import com.countingTree.Counting.Tree.App.dto.PlantDTO;
import com.countingTree.Counting.Tree.App.repository.PlantRepository;
import com.countingTree.Counting.Tree.App.service.PlantService;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Override
    public PlantDTO getPlantById(Long plantId) {
        Plant existingPlant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        return mapToDTO(existingPlant);
    }

    @Override
    public List<PlantDTO> getAllPlants() {
        return plantRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addPlant(Plant newPlant) {
        validatePlant(newPlant);
        plantRepository.save(newPlant);
    }

    @Override
    public PlantDTO updatePlant(Long plantId, Plant plant) {

        validatePlant(plant);
        Plant plantUpdated = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));

        plantUpdated.setSpecie(plant.getSpecie());
        plantUpdated.setPlantVerificationStatus(plant.getPlantVerificationStatus());
        plantUpdated.setHealthStatus(plant.getHealthStatus());
        plantUpdated.setPhotos(plant.getPhotos());
        plantUpdated.setNotes(plant.getNotes());
        plantUpdated.setAlerts(plant.getAlerts());
        plantRepository.save(plantUpdated);

        return mapToDTO(plantUpdated);
    }

    @Override
    public void deletePlant(Long plantId) {
        plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant with ID " + plantId + " not found."));
        plantRepository.deleteById(plantId);
    }

    // EXTRA METHODS
    private void validatePlant(Plant plant) {

        if (plant == null) {
            throw new IllegalArgumentException("Plant must not be null");
        }

        if (plant.getLatitude() == null || plant.getLongitude() == null) {
            throw new IllegalArgumentException("Plant must have a latitude and longitud coordenates.");
        }

        if (plant.getSpecie() == null) {
            throw new IllegalArgumentException("Plant must have a species");
        }

    }

    private PlantDTO mapToDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();

        plantDTO.setPlantId(plant.getPlantId());
        plantDTO.setLatitude(plant.getLatitude());
        plantDTO.setLongitude(plant.getLongitude());

        plantDTO.setDatePlanted(plant.getDatePlanted());

        plantDTO.setSpecieId(plant.getSpecie().getSpecieId());

        plantDTO.setPlantedById(plant.getPlantedBy().getUserId());

        plantDTO.setPlantVerificationStatus(plant.getPlantVerificationStatus().toString());

        if (plant.getHealthStatus() != null) {
            plantDTO.setHealthStatusId(plant.getHealthStatus().getStatusId());
        }

        Set<Long> photoIds = plant.getPhotos()
                .stream()
                .map(Photo::getPhotoId)
                .collect(Collectors.toSet());

        plantDTO.setPhotoIds(photoIds);

        Set<Long> notesIds = plant.getNotes()
                .stream()
                .map(Note::getNoteId)
                .collect(Collectors.toSet());

        plantDTO.setNoteIds(notesIds);

        Set<Long> alertIds = plant.getAlerts()
                .stream()
                .map(Alert::getAlertId)
                .collect(Collectors.toSet());

        plantDTO.setAlertIds(alertIds);

        return plantDTO;
    }
}
