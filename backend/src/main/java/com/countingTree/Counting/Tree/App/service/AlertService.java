package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.dto.AlertDTO;

public interface AlertService {

    AlertDTO getAlertById(Long id);

    List<AlertDTO> getAllAlerts();

    void addAlert(AlertDTO newAlert);

    AlertDTO updateAlert(Long id, AlertDTO alert);

    void deleteAlert(Long id);

}
