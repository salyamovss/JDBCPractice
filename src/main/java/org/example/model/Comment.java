package org.example.model;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Comment {
    private long id;
    private String text;
    private LocalDate commentDate;
    private Post postId;
    private User userId;

    public Comment(String text, LocalDate commentDate, Post postId, User userId) {
        this.text = text;
        this.commentDate = commentDate;
        this.postId = postId;
        this.userId = userId;
    }
}
