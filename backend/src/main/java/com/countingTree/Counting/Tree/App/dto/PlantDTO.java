package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;

import com.countingTree.Counting.Tree.App.model.Coordinate;
import com.countingTree.Counting.Tree.App.model.VerificationStatus;

public class PlantDTO {
    private String mainPhoto;
    private LocalDateTime datePlanted;
    private VerificationStatus verificationStatus;
    private Coordinate location;
    private Long speciesId;
    private Long ownerId;
    private Long healthStatusId;
    private Long zoneId;
}
