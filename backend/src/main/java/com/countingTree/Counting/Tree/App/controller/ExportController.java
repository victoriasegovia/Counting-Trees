package com.countingTree.Counting.Tree.App.controller;

import java.util.List;

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

import com.countingTree.Counting.Tree.App.model.Export;
import com.countingTree.Counting.Tree.App.service.ExportService;

@RestController
@RequestMapping("api/v1/exports")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping("/{id}")
    public ResponseEntity<Export> getExportById(@PathVariable Long id) {
        return ResponseEntity.ok(exportService.getExportById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addExport(@RequestBody Export export) {
        exportService.addExport(export);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExport(@PathVariable Long id, @RequestBody Export export) {
        exportService.updateExport(id, export);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExport(@PathVariable Long id) {
        exportService.deleteExport(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Export>> getAllExports() {
        return ResponseEntity.ok(exportService.getAllExports());
    }
}
