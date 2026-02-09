package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.AlertDTO;
import com.countingTree.Counting.Tree.App.model.Alert;

public interface AlertService {

    AlertDTO getAlertById(Long id);

    List<AlertDTO> getAllAlerts();

    void addAlert(Alert newAlert);

    AlertDTO updateAlert(Long id, Alert alert);

    void deleteAlert(Long id);

}
