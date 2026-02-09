package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.SpecieDTO;
import com.countingTree.Counting.Tree.App.model.Specie;

public interface SpecieService {

    SpecieDTO getSpecieById(Long specieId);

    List<SpecieDTO> getAllSpecies();

    void addSpecie(Specie newSpecie);

    SpecieDTO updateSpecie(Long specieId, Specie specie);

    void deleteSpecie(Long specieId);
}
