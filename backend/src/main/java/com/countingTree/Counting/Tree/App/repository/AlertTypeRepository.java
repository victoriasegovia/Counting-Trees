package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.countingTree.Counting.Tree.App.model.AlertType;

public interface AlertTypeRepository extends JpaRepository<AlertType, Long> {
    
}
