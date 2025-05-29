package org.example.dao;

import org.example.model.Post;

import java.util.List;

public interface PostDao {
    void createTable();

    void dropTable();

    void cleanTable();


    void savePost(Post post);

    List<Post> getPosts();

    Post getPostById(Long id);

    void updatePost(Long id,Post newPost);

    void deletePost(Long id);

    Long countPostsByUser(Long id);
}