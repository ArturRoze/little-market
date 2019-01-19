package com.app.domain.income;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String creationDate;
}
