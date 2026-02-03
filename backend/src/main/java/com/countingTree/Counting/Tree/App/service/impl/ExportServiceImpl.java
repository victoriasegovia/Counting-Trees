package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countingTree.Counting.Tree.App.model.Export;
import com.countingTree.Counting.Tree.App.repository.ExportRepository;
import com.countingTree.Counting.Tree.App.service.ExportService;

@Service
public class ExportServiceImpl implements ExportService {

    // WORK IN PROGRESS
    // Currently, these methods simulate export functionality.
    @Autowired
    private ExportRepository exportRepository;

    @Override
    public Export exportDataToEXCEL() {
        Export export = new Export();
        // export.setFormat(ExportFormat.EXCEL);
        // export.setExportDate(LocalDateTime.now());
        // export.setFilePath("/exports/data.xlsx"); // Ruta simulada
        // export.setGeneratedBy(user); // Asignar usuario si está disponible
        // exportRepository.save(export);
        return export;
    }

    @Override
    public Export exportDataToPDF() {
        Export export = new Export();
        // export.setFormat(ExportFormat.PDF);
        // export.setExportDate(LocalDateTime.now());
        // export.setFilePath("/exports/data.pdf"); // Ruta simulada
        // export.setGeneratedBy(user); // Asignar usuario si está disponible
        // exportRepository.save(export);
        return export;
    }

    @Override
    public Export exportDataToCSV() {
        Export export = new Export();
        // export.setFormat(ExportFormat.CSV);
        // export.setExportDate(LocalDateTime.now());
        // export.setFilePath("/exports/data.csv"); // Ruta simulada
        // // export.setGeneratedBy(user); // Asignar usuario si está disponible
        // exportRepository.save(export);
        return export;
    }

    @Override
    public Export getExportById(Long exportId) {
        return exportRepository.findById(exportId)
                .orElseThrow(() -> new IllegalArgumentException("Export with ID " + exportId + " not found."));
    }

    @Override
    public void addExport(Export newExport) {
        validateExport(newExport);
        exportRepository.save(newExport);
    }

    @Override
    public void updateExport(Long exportId, Export export) {
        // TODO
    }

    @Override
    public void deleteExport(Long exportId) {
        Export existingExport = exportRepository.findById(exportId)
                .orElseThrow(() -> new IllegalArgumentException("Export with ID " + exportId + " not found."));
        exportRepository.deleteById(exportId);
    }

    @Override
    public List<Export> getAllExports() {
        return exportRepository.findAll();
    }

    private void validateExport(Export export) {
        // TODO
    }
}
