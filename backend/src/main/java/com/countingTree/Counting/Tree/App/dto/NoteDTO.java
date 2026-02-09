package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDTO {
    
    private Long noteId;
    private String text;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private Long userId;
    private Long plantId;

}
