package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

import com.countingTree.Counting.Tree.App.model.Photo;
import com.countingTree.Counting.Tree.App.model.Plant;
import com.countingTree.Counting.Tree.App.model.User;
import com.countingTree.Counting.Tree.App.dto.PhotoDTO;
import com.countingTree.Counting.Tree.App.repository.PhotoRepository;
import com.countingTree.Counting.Tree.App.repository.PlantRepository;
import com.countingTree.Counting.Tree.App.repository.UserRepository;
import com.countingTree.Counting.Tree.App.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PhotoDTO getPhotoById(Long photoId) {
        Photo photoSearched = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo with ID " + photoId + " not found."));
        return mapToDTO(photoSearched);
    }

    @Override
    public List<PhotoDTO> getAllPhotos() {
        return photoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<PhotoDTO> getAllPhotosForPlant(Long plantId) {
        return photoRepository.findAll()
                .stream()
                .filter(photo -> photo.getPlant() != null && photo.getPlant().getPlantId().equals(plantId))
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PhotoDTO getPhotoForUser(Long userId) {
        Photo photo = photoRepository.findByUserUserId(userId);
        return mapToDTO(photo);
    }

    @Override
    public void addPhoto(Photo photo) {
        validatePhoto(photo);

        Plant plant = plantRepository.findById(photo.getPlant().getPlantId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Plant with ID " + photo.getPlant().getPlantId() + " not found."));
        User user = userRepository.findById(photo.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + photo.getUser().getUserId() + " not found."));

        photo.setPlant(plant);
        photo.setUser(user);

        photoRepository.save(photo);
    }

    @Override
    public PhotoDTO updatePhoto(Long photoId, Photo newPhoto) {
        Photo existingPhoto = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo with ID " + photoId + " not found."));

        validatePhoto(newPhoto);

        existingPhoto.setImageData(newPhoto.getImageData());
        // We probably don't want to change User or Plant on update unless specified,
        // but for simple CRUD:
        // existingPhoto.setUser(newPhoto.getUser());
        // existingPhoto.setPlant(newPhoto.getPlant());

        photoRepository.save(existingPhoto);
        return mapToDTO(existingPhoto);
    }

    @Override
    public void deletePhoto(Long photoId) {
        photoRepository.deleteById(photoId);
    }

    // ---------------------------------------------------- EXTRA METHODS

    private void validatePhoto(Photo photo) {

        if (photo.getImageData() == null) {
            throw new IllegalArgumentException("Photo data cannot be null or empty");
        }
        if (photo.getUser() == null) {
            throw new IllegalArgumentException("Photo user cannot be null");
        }
    }

    private PhotoDTO mapToDTO(Photo photo) {
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhotoId(photo.getPhotoId());
        photoDTO.setPlantId(photo.getPlant().getPlantId());
        photoDTO.setUserId(photo.getUser().getUserId());
        photoDTO.setUploadedAt(photo.getUploadedAt());
        photoDTO.setImageBase64(Base64.getEncoder().encodeToString(photo.getImageData()));

        return photoDTO;
    }

}
