package com.countingTree.Counting.Tree.App.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_modified", nullable = false)
    private LocalDateTime dateModified;


    // -------------------------------------------------------- RELATIONS

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-notes")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonBackReference("plant-notes")
    private Plant plant;

    
    // -------------------------------------------------------- CONSTRUCTORS, GETTERS AND SETTERS

    public Note(Long noteId, String text, LocalDateTime dateCreated, LocalDateTime dateModified, User user, Plant plant) {
        this.noteId = noteId;
        this.text = text;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.user = user;
        this.plant = plant;
    }

    public Note() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    
}
