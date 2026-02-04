package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.model.Photo;

public interface PhotoService {

    Photo getPlantPhotoById(Long photoId);

    List<Photo> getAllPlantPhotosForPlant(Long plantId);

    List<Photo> getAllPlantPhotos();

    void updatePlantPhoto(Long photoId, Photo photo);

    void deletePlantPhoto(Long photoId);

    void addPlantPhoto(Long plantId, Photo photo);
}
