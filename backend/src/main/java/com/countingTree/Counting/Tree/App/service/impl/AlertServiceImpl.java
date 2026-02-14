package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.countingTree.Counting.Tree.App.dto.*;
import com.countingTree.Counting.Tree.App.model.*;
import com.countingTree.Counting.Tree.App.repository.AlertRepository;
import com.countingTree.Counting.Tree.App.service.AlertService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public AlertDTO getAlertById(Long id) {
        Alert alertSearched = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert with ID " + id + " not found"));

        return mapToDTO(alertSearched);
    }

    @Override
    public List<AlertDTO> getAllAlerts() {
        return alertRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addAlert(Alert newAlert) {
        validateNewAlert(newAlert);
        alertRepository.save(newAlert);
    }

    @Override
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }

    @Override
    public AlertDTO updateAlert(Long id, Alert alert) {

        Alert alertToUpdate = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert with ID " + id + " not found"));

        java.util.Optional.ofNullable(alert.getAlertType()).ifPresent(alertToUpdate::setAlertType);
        java.util.Optional.ofNullable(alert.getStatus()).ifPresent(alertToUpdate::setStatus);
        java.util.Optional.ofNullable(alert.getResolvedBy()).ifPresent(alertToUpdate::setResolvedBy);
        alertRepository.save(alertToUpdate);

        return mapToDTO(alertToUpdate);
    }

    // -------------------------- EXTRA METHODS

    public void validateNewAlert(Alert alert) {

        if (alert.getAlertType() == null) {
            throw new IllegalArgumentException("Alert type cannot be null or empty");
        }
        if (alert.getCreationDate() == null) {
            throw new IllegalArgumentException("Alert creation date cannot be null");
        }
        if (alert.getStatus() == null) {
            throw new IllegalArgumentException("Alert status cannot be null or empty");
        }
        if (alert.getCreatedBy() == null) {
            throw new IllegalArgumentException("Alert creator cannot be null");
        }

    }

    private AlertDTO mapToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();

        dto.setAlertId(alert.getAlertId());
        dto.setCreationDate(alert.getCreationDate());
        dto.setStatus(alert.getStatus());
        dto.setCreatedById(alert.getCreatedBy().getUserId());

        if (alert.getResolvedBy() != null) {
            dto.setResolvedById(alert.getResolvedBy().getUserId());
        }

        dto.setPlantId(alert.getPlant().getPlantId());

        return dto;
    }

}
