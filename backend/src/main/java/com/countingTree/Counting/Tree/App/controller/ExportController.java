package com.countingTree.Counting.Tree.App.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exports")
public class ExportController {

    // TODO
    // @Autowired
    // private ExportService exportService;

    // @GetMapping("/{id}")
    // public ResponseEntity<Export> getExportById(@PathVariable Long id) {
    //     return ResponseEntity.ok(exportService.getExportById(id));
    // }

    // @PostMapping
    // public ResponseEntity<Void> addExport(@RequestBody Export export) {
    //     exportService.addExport(export);
    //     return ResponseEntity.ok().build();
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Void> updateExport(@PathVariable Long id, @RequestBody Export export) {
    //     exportService.updateExport(id, export);
    //     return ResponseEntity.ok().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteExport(@PathVariable Long id) {
    //     exportService.deleteExport(id);
    //     return ResponseEntity.ok().build();
    // }

    // @GetMapping
    // public ResponseEntity<List<Export>> getAllExports() {
    //     return ResponseEntity.ok(exportService.getAllExports());
    // }

}
