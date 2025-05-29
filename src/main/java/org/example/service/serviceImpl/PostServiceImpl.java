package org.example.service.serviceImpl;

import org.example.dao.PostDao;
import org.example.dao.daoImpl.PostDaoImpl;
import org.example.model.Post;
import org.example.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    PostDaoImpl postDao = new PostDaoImpl();

    @Override
    public void crateTable() {
        postDao.createTable();
    }

    @Override
    public void dropTable() {
        postDao.dropTable();
    }

    @Override
    public void cleanTable() {
        postDao.cleanTable();
    }

    @Override
    public void savePost(Post post) {
        postDao.savePost(post);
    }

    @Override
    public List<Post> getPosts() {
        return postDao.getPosts();
    }

    @Override
    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        postDao.updatePost(id, newPost);
    }

    @Override
    public void deletePost(Long id) {
        postDao.deletePost(id);
    }

    @Override
    public Long countPostsByUser(Long id) {
        return postDao.countPostsByUser(id);
    }
}