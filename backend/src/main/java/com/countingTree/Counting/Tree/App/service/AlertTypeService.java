package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.AlertTypeDTO;
import com.countingTree.Counting.Tree.App.model.AlertType;

public interface AlertTypeService {

    AlertTypeDTO getAlertTypeById(Long id);

    List<AlertTypeDTO> getAllAlertTypes();

    void addAlertType(AlertType newAlertType);

    AlertTypeDTO updateAlertType(Long id, AlertType alert);

    void deleteAlertType(Long id);

}
