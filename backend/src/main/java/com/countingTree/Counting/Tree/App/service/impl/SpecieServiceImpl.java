package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.countingTree.Counting.Tree.App.model.Specie;
import com.countingTree.Counting.Tree.App.dto.SpecieDTO;
import com.countingTree.Counting.Tree.App.repository.SpecieRepository;
import com.countingTree.Counting.Tree.App.service.SpecieService;

import org.springframework.stereotype.Service;

@Service
public class SpecieServiceImpl implements SpecieService {

    @Autowired
    private SpecieRepository specieRepository;

    @Override
    public SpecieDTO getSpecieById(Long specieId) {
        Specie existingSpecie = specieRepository.findById(specieId)
                .orElseThrow(() -> new IllegalArgumentException("Specie with ID " + specieId + " not found."));
        return mapToDTO(existingSpecie);
    }

    @Override
    public List<SpecieDTO> getAllSpecies() {
        return specieRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addSpecie(Specie newSpecie) {
        validateSpecie(newSpecie);
        specieRepository.save(newSpecie);
    }

    @Override
    public SpecieDTO updateSpecie(Long specieId, Specie specie) {
        validateSpecie(specie);
        Specie specieUpdate = specieRepository.findById(specieId)
                .orElseThrow(() -> new IllegalArgumentException("Specie with ID " + specieId + " not found."));

        java.util.Optional.ofNullable(specie.getCommonName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(specieUpdate::setCommonName);
        java.util.Optional.ofNullable(specie.getScientificName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(specieUpdate::setScientificName);
        java.util.Optional.ofNullable(specie.getDescription()).ifPresent(specieUpdate::setDescription);
        specieRepository.save(specieUpdate);

        return mapToDTO(specieUpdate);
    }

    @Override
    public void deleteSpecie(Long specieId) {
        if (!specieRepository.existsById(specieId)) {
            throw new IllegalArgumentException("Specie with ID " + specieId + " not found.");
        }
        specieRepository.deleteById(specieId);
    }

    // -------------------------- EXTRA METHODS
    private void validateSpecie(Specie specie) {

        if (specie == null) {
            throw new IllegalArgumentException("Specie must not be null");
        }
        if (specie.getCommonName() == null || specie.getCommonName().trim().isEmpty()) {
            throw new IllegalArgumentException("Common name must not be null or empty");
        }
        if (specie.getScientificName() == null || specie.getScientificName().trim().isEmpty()) {
            throw new IllegalArgumentException("Scientific name must not be null or empty");
        }

        Specie existingByCommonName = specieRepository.findByCommonName(specie.getCommonName());

        if (existingByCommonName != null && !existingByCommonName.getSpecieId().equals(specie.getSpecieId())) {
            throw new IllegalArgumentException("Specie common name already exists.");
        }

        Specie existingByScientificName = specieRepository.findByScientificName(specie.getScientificName());

        if (existingByScientificName != null && !existingByScientificName.getSpecieId().equals(specie.getSpecieId())) {
            throw new IllegalArgumentException("Specie scientific name already exists.");
        }
    }

    private SpecieDTO mapToDTO(Specie specie) {
        SpecieDTO specieDTO = new SpecieDTO();

        specieDTO.setSpecieId(specie.getSpecieId());
        specieDTO.setCommonName(specie.getCommonName());
        specieDTO.setScientificName(specie.getScientificName());
        specieDTO.setDescription(specie.getDescription());

        return specieDTO;
    }
}
