package com.countingTree.Counting.Tree.App.service.impl;

import com.countingTree.Counting.Tree.App.model.HealthStatus;
import com.countingTree.Counting.Tree.App.dto.HealthStatusDTO;
import com.countingTree.Counting.Tree.App.repository.HealthStatusRepository;
import com.countingTree.Counting.Tree.App.service.HealthStatusService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

@Service
public class HealthStatusServiceImpl implements HealthStatusService {

    @Autowired
	private HealthStatusRepository healthStatusRepository;

	@Override
	public HealthStatusDTO getHealthStatusById(Long healthStatusId) {
		HealthStatus healthStatus = healthStatusRepository.findById(healthStatusId)
                .orElseThrow(() -> new EntityNotFoundException("Health Status with ID " + healthStatusId + " not found."));
	return mapToDTO(healthStatus);
			}

	@Override
	public List<HealthStatusDTO> getAllHealthStatus() {
		return healthStatusRepository.findAll()
			.stream()
			.map(this::mapToDTO)
			.toList();
	}

	@Override
	public void addHealthStatus(HealthStatus newHealthStatus) {
        validateHealthStatus(newHealthStatus);
		healthStatusRepository.save(newHealthStatus);
	}

	@Override
	public HealthStatusDTO updateHealthStatus(Long healthStatusId, HealthStatus healthStatus) {
		validateHealthStatus(healthStatus);
		HealthStatus existingHealthStatus = healthStatusRepository.findById(healthStatusId)
                .orElseThrow(() -> new EntityNotFoundException("Health Status with ID " + healthStatusId + " not found."));
        
		existingHealthStatus.setName(healthStatus.getName());
		existingHealthStatus.setDescription(healthStatus.getDescription());
		healthStatusRepository.save(existingHealthStatus);

		return mapToDTO(existingHealthStatus);
	}

	@Override
	public void deleteHealthStatus(Long healthStatusId) {
        HealthStatus existing = healthStatusRepository.findById(healthStatusId)
                .orElseThrow(() -> new EntityNotFoundException("Health Status with ID " + healthStatusId + " not found."));
        
        if (!existing.getPlants().isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This Health Status has associated plants and cannot be erased.");
        }
        
		healthStatusRepository.deleteById(healthStatusId);
	}

    // ------------------------ EXTRA METHODS

    private void validateHealthStatus(HealthStatus healthStatus) {
        
		if (healthStatus.getStatusId() == null) {
            throw new IllegalArgumentException("Health status ID cannot be null");
        }
		if (healthStatus.getName() == null || healthStatus.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Health status name cannot be null or empty");
        }
        if (healthStatusRepository.existsByName(healthStatus.getName())) {
            throw new IllegalArgumentException("Health status with the same name already exists");
        }

    }

	private HealthStatusDTO mapToDTO(HealthStatus healthStatus) {
		HealthStatusDTO healthStatusDTO = new HealthStatusDTO();

		healthStatusDTO.setStatusId(healthStatus.getStatusId());
		healthStatusDTO.setName(healthStatus.getName());
		healthStatusDTO.setDescription(healthStatus.getDescription());

		return healthStatusDTO;
	}

}
