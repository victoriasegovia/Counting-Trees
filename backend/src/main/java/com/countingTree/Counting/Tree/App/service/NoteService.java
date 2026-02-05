package com.countingTree.Counting.Tree.App.service;

import java.util.List;

import com.countingTree.Counting.Tree.App.dto.NoteDTO;
import com.countingTree.Counting.Tree.App.model.Note;

public interface NoteService {

    NoteDTO getNoteDTOById(Long noteId);

    List<NoteDTO> getAllNotes();

    void addNote(Note newNote);

    NoteDTO updateNote(Long noteId, Note note);

    void deleteNote(Long noteId);

}
