package org.example.ormfinalproject.model;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString

public class UserDTO {
    private long userId;
    private String name;
    private String password;
    private String role;
}
