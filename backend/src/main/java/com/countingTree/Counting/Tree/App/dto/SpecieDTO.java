package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecieDTO {

    private Long specieId;
    private String commonName;
    private String scientificName;
    private String description;

}
