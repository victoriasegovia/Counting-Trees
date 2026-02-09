package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.model.Photo;
import com.countingTree.Counting.Tree.App.dto.PhotoDTO;

public interface PhotoService {

    PhotoDTO getPhotoById(Long photoId);

    List<PhotoDTO> getAllPhotosForPlant(Long plantId);

    PhotoDTO getPhotoForUser(Long userId);

    List<PhotoDTO> getAllPhotos();

    void addPhoto(Photo photo);

    PhotoDTO updatePhoto(Long photoId, Photo photo);

    void deletePhoto(Long photoId);

}
