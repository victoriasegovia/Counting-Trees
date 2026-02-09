package com.countingTree.Counting.Tree.App.controller;

import java.util.List;

import com.countingTree.Counting.Tree.App.dto.AlertDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.countingTree.Counting.Tree.App.model.Alert;
import com.countingTree.Counting.Tree.App.service.AlertService;

@RestController
@RequestMapping("api/v1/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.getAlertById(id));
    }

    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @PostMapping
    public ResponseEntity<Void> addAlert(@RequestBody Alert alert) {
        alertService.addAlert(alert);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        AlertDTO updated = alertService.updateAlert(id, alert);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.ok().build();
    }

}
