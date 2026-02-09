package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDTO {

    private Long photoId;
    private Long plantId;
    private Long userId;
    private LocalDateTime uploadedAt;
    private String imageBase64;

}
