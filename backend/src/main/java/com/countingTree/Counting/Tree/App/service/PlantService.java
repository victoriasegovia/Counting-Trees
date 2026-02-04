package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.model.Plant;

public interface PlantService {

    void addPlant(Plant newPlant);

    void updatePlant(Long plantId, Plant newPlant);

    void deletePlant(Long plantId);

    Plant getPlant(Long plantId);

    List<Plant> getAllPlants();
    
}