package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countingTree.Counting.Tree.App.dto.AlertTypeDTO;
import com.countingTree.Counting.Tree.App.model.AlertType;
import com.countingTree.Counting.Tree.App.repository.AlertTypeRepository;
import com.countingTree.Counting.Tree.App.service.AlertTypeService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlertTypeServiceImpl implements AlertTypeService {

    @Autowired
    private AlertTypeRepository alertTypeRepository;

    @Override
    public AlertTypeDTO getAlertTypeById(Long id) {
        AlertType alertType = alertTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AlertType with ID " + id + " not found"));
        return mapToDTO(alertType);
    }

    @Override
    public List<AlertTypeDTO> getAllAlertTypes() {
        return alertTypeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addAlertType(AlertType newAlertType) {
        validateAlertType(newAlertType);
        alertTypeRepository.save(newAlertType);
    }

    @Override
    public AlertTypeDTO updateAlertType(Long id, AlertType alertType) {
        validateAlertType(alertType);
        AlertType alertTypeUpdated = alertTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert type with ID " + id + " not found"));

        java.util.Optional.ofNullable(alertType.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(alertTypeUpdated::setName);
        java.util.Optional.ofNullable(alertType.getDescription()).ifPresent(alertTypeUpdated::setDescription);
        alertTypeRepository.save(alertTypeUpdated);

        return mapToDTO(alertTypeUpdated);
    }

    @Override
    public void deleteAlertType(Long id) {
        if (!alertTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("AlertType with ID " + id + " not found.");
        }
        alertTypeRepository.deleteById(id);
    }

    // -------------------------- EXTRA METHODS
    private void validateAlertType(AlertType alertType) {

        AlertType existing = alertTypeRepository.findByName(alertType.getName());

        if (alertType.getName() == null) {
            throw new IllegalArgumentException("New alert type name cannot be null.");
        }

        if (existing != null && !existing.getAlertTypeId().equals(alertType.getAlertTypeId())) {
            throw new IllegalArgumentException("New alert type name already exist.");
        }
    }

    private AlertTypeDTO mapToDTO(AlertType alertType) {
        AlertTypeDTO alertTypeDTO = new AlertTypeDTO();

        alertTypeDTO.setAlertTypeId(alertType.getAlertTypeId());
        alertTypeDTO.setName(alertType.getName());
        alertTypeDTO.setDescription(alertType.getDescription());

        return alertTypeDTO;
    }

}
