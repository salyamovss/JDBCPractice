package org.example.model;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Post {
    private long id;
    private String image;
    private String description;
    private LocalDate createdDate;
    private long userId;

    public Post(String image, String description, LocalDate createdDate, long userId) {
        this.image = image;
        this.description = description;
        this.createdDate = createdDate;
        this.userId = userId;
    }
}
