package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Log;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllLogsByUserId(Long userId);
}
