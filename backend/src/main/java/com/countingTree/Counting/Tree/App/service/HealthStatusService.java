package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.model.HealthStatus;

public interface HealthStatusService {
    
    HealthStatus getHealthStatusById(Long healthStatusId);

    void updateHealthStatus(Long healthStatusId, HealthStatus healthStatus);

    void deleteHealthStatus(Long healthStatusId);

    void addHealthStatus(HealthStatus newHealthStatus);

    List<HealthStatus> getAllHealthStatus();

}
