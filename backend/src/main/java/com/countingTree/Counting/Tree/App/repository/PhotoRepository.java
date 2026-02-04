package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    
}
