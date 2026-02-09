package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;

public class NoteDTO {
    
    private Long noteId;
    private String text;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private Long userId;
    private Long plantId;

    public NoteDTO() {}

    public NoteDTO(Long noteId, String text, LocalDateTime dateCreated, LocalDateTime dateModified, Long userId,
            Long plantId) {
        this.noteId = noteId;
        this.text = text;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.userId = userId;
        this.plantId = plantId;
    }

    public Long getNoteId() {
        return noteId;
    }
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    public LocalDateTime getDateModified() {
        return dateModified;
    }
    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getPlantId() {
        return plantId;
    }
    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    
}
