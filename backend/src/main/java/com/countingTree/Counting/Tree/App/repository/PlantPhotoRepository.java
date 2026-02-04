package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Photo;

public interface PlantPhotoRepository extends JpaRepository<Photo, Long> {
    
}
