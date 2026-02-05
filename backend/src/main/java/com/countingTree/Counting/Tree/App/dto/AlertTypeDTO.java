package com.countingTree.Counting.Tree.App.dto;

public class AlertTypeDTO {
    
    private Long alertTypeId;
    private String name;
    private String description;

    public AlertTypeDTO() { }

    public AlertTypeDTO(Long alertTypeId, String name, String description) {
        this.alertTypeId = alertTypeId;
        this.name = name;
        this.description = description;
    }

    public Long getAlertTypeId() {
        return alertTypeId;
    }

    public void setAlertTypeId(Long alertTypeId) {
        this.alertTypeId = alertTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
