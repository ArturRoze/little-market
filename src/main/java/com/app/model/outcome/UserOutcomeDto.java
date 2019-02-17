package com.app.model.outcome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutcomeDto {
    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String creationDate;
}
