package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.HealthStatusDTO;
import com.countingTree.Counting.Tree.App.model.HealthStatus;

public interface HealthStatusService {
    
    HealthStatusDTO getHealthStatusById(Long healthStatusId);

    List<HealthStatusDTO> getAllHealthStatus();

    void addHealthStatus(HealthStatus newHealthStatus);

    HealthStatusDTO updateHealthStatus(Long healthStatusId, HealthStatus healthStatus);

    void deleteHealthStatus(Long healthStatusId);
}
