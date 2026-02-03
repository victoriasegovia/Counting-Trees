package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.model.PlantPhoto;

public interface PlantPhotoService {

    PlantPhoto getPlantPhotoById(Long photoId);

    List<PlantPhoto> getAllPlantPhotosForPlant(Long plantId);

    List<PlantPhoto> getAllPlantPhotos();

    void updatePlantPhoto(Long photoId, PlantPhoto photo);

    void deletePlantPhoto(Long photoId);

    void addPlantPhoto(Long plantId, PlantPhoto photo);
}
