package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.model.Export;

public interface ExportService {

    Export exportDataToEXCEL();

    Export exportDataToPDF();

    Export exportDataToCSV();

    Export getExportById(Long exportId);

    void addExport(Export newExport);

    void updateExport(Long exportId, Export export);

    void deleteExport(Long exportId);

    List<Export> getAllExports();

}
