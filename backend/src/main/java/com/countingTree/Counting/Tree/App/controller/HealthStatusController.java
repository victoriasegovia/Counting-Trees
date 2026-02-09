package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.HealthStatusDTO;
import com.countingTree.Counting.Tree.App.model.HealthStatus;
import com.countingTree.Counting.Tree.App.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/health-statuses")
public class HealthStatusController {

    @Autowired
    private HealthStatusService healthStatusService;

    @GetMapping("/{id}")
    public ResponseEntity<HealthStatusDTO> getHealthStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(healthStatusService.getHealthStatusById(id));
    }

    @GetMapping
    public ResponseEntity<List<HealthStatusDTO>> getAllHealthStatuses() {
        return ResponseEntity.ok(healthStatusService.getAllHealthStatus());
    }

    @PostMapping
    public ResponseEntity<Void> addHealthStatus(@RequestBody HealthStatus healthStatus) {
        healthStatusService.addHealthStatus(healthStatus);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthStatusDTO> updateHealthStatus(@PathVariable Long id, @RequestBody HealthStatus healthStatus) {
        HealthStatusDTO updated = healthStatusService.updateHealthStatus(id, healthStatus);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthStatus(@PathVariable Long id) {
        healthStatusService.deleteHealthStatus(id);
        return ResponseEntity.ok().build();
    }

}
