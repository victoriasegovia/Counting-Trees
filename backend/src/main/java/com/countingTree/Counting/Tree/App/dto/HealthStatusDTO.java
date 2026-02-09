package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthStatusDTO {
    
    private Long statusId;
    private String name;
    private String description;

}
