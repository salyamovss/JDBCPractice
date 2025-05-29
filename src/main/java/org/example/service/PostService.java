package org.example.service;

import org.example.model.Post;

import java.util.List;

public interface PostService {
    void crateTable();

    void dropTable();

    void cleanTable();

    void savePost(Post post);

    List<Post> getPosts();

    Post getPostById(Long id);

    void updatePost(Long id,Post newPost);

    void deletePost(Long id);

    Long countPostsByUser(Long id);
}