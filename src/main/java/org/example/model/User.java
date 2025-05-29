package org.example.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor

public class User {
    private long id;
    private String userName;
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
