package com.app.domain.outcome;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserOutcomeDto {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String creationDate;
}
