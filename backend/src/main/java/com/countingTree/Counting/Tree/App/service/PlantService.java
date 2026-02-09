package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.PlantDTO;
import com.countingTree.Counting.Tree.App.model.Plant;

public interface PlantService {

    PlantDTO getPlantById(Long plantId);

    List<PlantDTO> getAllPlants();

    void addPlant(Plant newPlant);

    PlantDTO updatePlant(Long plantId, Plant newPlant);

    void deletePlant(Long plantId);

}