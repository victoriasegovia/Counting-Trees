package com.countingTree.Counting.Tree.App.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String imageBase64;

}
