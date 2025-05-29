package org.example.dao;

import org.example.model.Comment;

import java.util.List;

public interface CommentDao {

    void createTable();

    void dropTable();

    void cleanTable();



    void saveComment(Comment comment);

    List<Comment> getComments();

    Comment getCommentById(Long id);

    void updateComment(Long id,Comment newComment);

    void deleteCommentById(Long id);

    List<Comment> findRecentCommentsByPost(Long postId,int limit);

    Long countCommentsByPost(Long postId);
}