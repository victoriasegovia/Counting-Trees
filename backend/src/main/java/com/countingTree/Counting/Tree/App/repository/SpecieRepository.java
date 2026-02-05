package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Specie;

public interface SpecieRepository extends JpaRepository<Specie, Long> {

    Specie findByName(String commonName);

    Specie findByCommonName(String commonName);

    Specie findByScientificName(String scientificName);
    
}
