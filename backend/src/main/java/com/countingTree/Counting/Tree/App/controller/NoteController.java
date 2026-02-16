package com.countingTree.Counting.Tree.App.controller;

import com.countingTree.Counting.Tree.App.dto.NoteDTO;
import com.countingTree.Counting.Tree.App.model.Note;
import com.countingTree.Counting.Tree.App.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<Void> addNote(@RequestBody Note note) {
        noteService.addNote(note);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable Long id, @RequestBody Note note) {
        NoteDTO updated = noteService.updateNote(id, note);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
