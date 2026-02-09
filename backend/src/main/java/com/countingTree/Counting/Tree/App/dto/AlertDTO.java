package com.countingTree.Counting.Tree.App.dto;

import java.time.LocalDateTime;

import com.countingTree.Counting.Tree.App.model.AlertStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDTO {

    private Long alertId;
    private String alertTypeName;
    private String alertTypeDescription;
    private LocalDateTime creationDate;
    private AlertStatus status;
    private Long createdById;
    private String createdByName;
    private Long resolvedById;
    private String resolvedByName;
    private Long plantId;

}
