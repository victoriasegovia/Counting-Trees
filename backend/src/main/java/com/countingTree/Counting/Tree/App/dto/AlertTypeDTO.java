package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertTypeDTO {
    
    private Long alertTypeId;
    private String name;
    private String description;

}
