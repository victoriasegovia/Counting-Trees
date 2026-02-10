package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantDTO {

    private Long plantId;
    private Double latitude;
    private Double longitude;

    private LocalDateTime datePlanted;
    
    private Long specieId;
    private Long plantedById;

    private String plantVerificationStatus;

    private Long healthStatusId;

    private Set<Long> photoIds;
    private Set<Long> noteIds;
    private Set<Long> alertIds;

}

