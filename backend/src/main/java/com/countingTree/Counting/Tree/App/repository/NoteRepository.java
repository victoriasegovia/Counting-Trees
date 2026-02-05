package com.countingTree.Counting.Tree.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countingTree.Counting.Tree.App.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
