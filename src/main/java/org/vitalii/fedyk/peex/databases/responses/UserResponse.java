package org.vitalii.fedyk.peex.databases.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String password;
    private String created;
}
