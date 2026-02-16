package com.countingTree.Counting.Tree.App.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countingTree.Counting.Tree.App.dto.NoteDTO;
import com.countingTree.Counting.Tree.App.model.Note;
import com.countingTree.Counting.Tree.App.repository.NoteRepository;
import com.countingTree.Counting.Tree.App.service.NoteService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public NoteDTO getNoteById(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note with ID " + noteId + " not found"));
        return mapToDTO(note);
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void addNote(Note newNote) {
        validateNote(newNote);
        noteRepository.save(newNote);
    }

    @Override
    public NoteDTO updateNote(Long noteId, Note note) {
        validateNote(note);
        Note noteUpdated = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Alert type with ID " + noteId + " not found"));

        java.util.Optional.ofNullable(note.getText())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(noteUpdated::setText);
        java.util.Optional.ofNullable(note.getDateModified()).ifPresent(noteUpdated::setDateModified);
        noteRepository.save(noteUpdated);

        return mapToDTO(noteUpdated);
    }

    @Override
    public void deleteNote(Long noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new IllegalArgumentException("Note with ID " + noteId + " not found.");
        }
        noteRepository.deleteById(noteId);
    }

    // --------------------------------------------------------- EXTRA METHODS

    private NoteDTO mapToDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();

        noteDTO.setNoteId(note.getNoteId());
        noteDTO.setText(note.getText());
        noteDTO.setDateCreated(note.getDateCreated());
        noteDTO.setDateModified(note.getDateModified());
        noteDTO.setUserId(note.getUser().getUserId());
        noteDTO.setPlantId(note.getPlant().getPlantId());

        return noteDTO;
    }

    private void validateNote(Note note) {

        if (note.getText() == null || note.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Note text cannot be null or empty");
        }
        if (note.getUser() == null || note.getUser().getUserId() == null) {
            throw new IllegalArgumentException("Note must be associated with a valid user");
        }
        if (note.getPlant() == null || note.getPlant().getPlantId() == null) {
            throw new IllegalArgumentException("Note must be associated with a valid plant");
        }
    }

}
