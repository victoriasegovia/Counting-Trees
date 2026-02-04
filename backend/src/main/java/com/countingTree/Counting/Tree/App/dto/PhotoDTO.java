package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;

public class PhotoDTO {

    private Long photoId;
    private Long plantId;      // ID de la planta asociada
    private Long userId;       // ID del usuario propietario
    private LocalDateTime uploadedAt;
    private String imageBase64; // opcional: convertir byte[] a Base64

    // -------------------------------------------------------- CONSTRUCTORS

    public PhotoDTO() {}

    public PhotoDTO(Long photoId, Long plantId, Long userId, LocalDateTime uploadedAt, String imageBase64) {
        this.photoId = photoId;
        this.plantId = plantId;
        this.userId = userId;
        this.uploadedAt = uploadedAt;
        this.imageBase64 = imageBase64;
    }

    // -------------------------------------------------------- GETTERS Y SETTERS

    public Long getPhotoId() { return photoId; }
    public void setPhotoId(Long photoId) { this.photoId = photoId; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
}
