package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Note;

public interface CommentRepository extends JpaRepository<Note, Long> {
    
}
