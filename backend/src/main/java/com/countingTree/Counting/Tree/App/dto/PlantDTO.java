package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class PlantDTO {

    private Long plantId;
    private Double latitude;
    private Double longitude;

    private LocalDateTime datePlanted;
    
    private Long specieId;
    private String specieCommonName;
    private String specieScientificName;

    private Long plantedById;
    private String plantedByName;

    private String plantVerificationStatus;
    
    private Long healthStatusId;
    private String healthStatusName;
    private String healthStatusDescription;

    private Set<Long> photoIds;

    private Set<Long> alertIds;

    // -------------------------------------------------------- CONSTRUCTORS
    public PlantDTO() {}

    public PlantDTO(Long plantId, Double latitude, Double longitude, LocalDateTime datePlanted,
                    Long specieId, String specieCommonName, String specieScientificName,
                    Long plantedById, String plantedByName,
                    String plantVerificationStatus,
                    Long healthStatusId, String healthStatusName, String healthStatusDescription,
                    Set<Long> photoIds, Set<Long> alertIds) {
        this.plantId = plantId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.datePlanted = datePlanted;
        this.specieId = specieId;
        this.specieCommonName = specieCommonName;
        this.specieScientificName = specieScientificName;
        this.plantedById = plantedById;
        this.plantedByName = plantedByName;
        this.plantVerificationStatus = plantVerificationStatus;
        this.healthStatusId = healthStatusId;
        this.healthStatusName = healthStatusName;
        this.healthStatusDescription = healthStatusDescription;
        this.photoIds = photoIds;
        this.alertIds = alertIds;
    }

    // -------------------------------------------------------- GETTERS Y SETTERS
    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    public LocalDateTime getDatePlanted() { return datePlanted; }
    public void setDatePlanted(LocalDateTime datePlanted) { this.datePlanted = datePlanted; }

    public Long getSpecieId() { return specieId; }
    public void setSpecieId(Long specieId) { this.specieId = specieId; }

    public String getSpecieCommonName() { return specieCommonName; }
    public void setSpecieCommonName(String specieCommonName) { this.specieCommonName = specieCommonName; }

    public String getSpecieScientificName() { return specieScientificName; }
    public void setSpecieScientificName(String specieScientificName) { this.specieScientificName = specieScientificName; }

    public Long getPlantedById() { return plantedById; }
    public void setPlantedById(Long plantedById) { this.plantedById = plantedById; }

    public String getPlantedByName() { return plantedByName; }
    public void setPlantedByName(String plantedByName) { this.plantedByName = plantedByName; }

    public String getPlantVerificationStatus() { return plantVerificationStatus; }
    public void setPlantVerificationStatus(String plantVerificationStatus) { this.plantVerificationStatus = plantVerificationStatus; }

    public Long getHealthStatusId() { return healthStatusId; }
    public void setHealthStatusId(Long healthStatusId) { this.healthStatusId = healthStatusId; }

    public String getHealthStatusName() { return healthStatusName; }
    public void setHealthStatusName(String healthStatusName) { this.healthStatusName = healthStatusName; }

    public String getHealthStatusDescription() { return healthStatusDescription; }
    public void setHealthStatusDescription(String healthStatusDescription) { this.healthStatusDescription = healthStatusDescription; }

    public Set<Long> getPhotoIds() { return photoIds; }
    public void setPhotos(Set<Long> photoIds) { this.photoIds = photoIds; }

    public Set<Long> getAlertIds() { return alertIds; }
    public void setAlertIds(Set<Long> alertIds) { this.alertIds = alertIds; }

}

