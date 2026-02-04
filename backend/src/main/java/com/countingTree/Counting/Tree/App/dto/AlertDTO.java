package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;

import com.countingTree.Counting.Tree.App.model.AlertStatus;

public class AlertDTO {

    private String alertTypeName;
    private String alertTypeDescription;
    private LocalDateTime creationDate;
    private AlertStatus status;
    private Long createdById;
    private String createdByName;
    private Long resolvedById;
    private String resolvedByName;
    private Long plantId;

    public AlertDTO() {
    }

    public AlertDTO(String alertTypeName, String alertTypeDescription, LocalDateTime creationDate, AlertStatus status,
            Long createdById, String createdByName, Long resolvedById, String resolvedByName, Long plantId) {
        this.alertTypeName = alertTypeName;
        this.alertTypeDescription = alertTypeDescription;
        this.creationDate = creationDate;
        this.status = status;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.resolvedById = resolvedById;
        this.resolvedByName = resolvedByName;
        this.plantId = plantId;
    }

    public String getAlertTypeName() {
        return alertTypeName;
    }

    public void setAlertTypeName(String alertTypeName) {
        this.alertTypeName = alertTypeName;
    }

    public String getAlertTypeDescription() {
        return alertTypeDescription;
    }

    public void setAlertTypeDescription(String alertTypeDescription) {
        this.alertTypeDescription = alertTypeDescription;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getResolvedById() {
        return resolvedById;
    }

    public void setResolvedById(Long resolvedById) {
        this.resolvedById = resolvedById;
    }

    public String getResolvedByName() {
        return resolvedByName;
    }

    public void setResolvedByName(String resolvedByName) {
        this.resolvedByName = resolvedByName;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

}
