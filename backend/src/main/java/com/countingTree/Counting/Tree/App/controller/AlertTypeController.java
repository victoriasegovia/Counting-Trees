package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.AlertTypeDTO;
import com.countingTree.Counting.Tree.App.model.AlertType;
import com.countingTree.Counting.Tree.App.service.AlertTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/alert-types")
public class AlertTypeController {

    @Autowired
    private AlertTypeService alertTypeService;

    @GetMapping
    public ResponseEntity<List<AlertTypeDTO>> getAllAlertTypes() {
        return ResponseEntity.ok(alertTypeService.getAllAlertTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertTypeDTO> getAlertTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(alertTypeService.getAlertTypeById(id));
    }

    @PostMapping
    public ResponseEntity<AlertType> addAlertType(@RequestBody AlertType newAlertType) {
        alertTypeService.addAlertType(newAlertType);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertTypeDTO> updateAlertType(@PathVariable Long id, @RequestBody AlertType alertType) {
        AlertTypeDTO updated = alertTypeService.updateAlertType(id, alertType);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlertType(@PathVariable Long id) {
        alertTypeService.deleteAlertType(id);
        return ResponseEntity.noContent().build();
    }
}
