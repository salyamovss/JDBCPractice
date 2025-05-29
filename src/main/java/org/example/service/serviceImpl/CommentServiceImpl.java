package org.example.service.serviceImpl;

import org.example.dao.CommentDao;
import org.example.dao.daoImpl.CommentDaoImpl;
import org.example.model.Comment;
import org.example.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public void createTable() {
        commentDao.createTable();
    }

    @Override
    public void dropTable() {
        commentDao.dropTable();
    }

    @Override
    public void cleanTable() {
        commentDao.cleanTable();
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> getComments() {
        return commentDao.getComments();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public void updateComment(Long id, Comment newComment) {
        commentDao.updateComment(id, newComment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentDao.deleteCommentById(id);
    }

    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        return commentDao.findRecentCommentsByPost(postId, limit);
    }

    @Override
    public Long countCommentsByPost(Long postId) {
        return commentDao.countCommentsByPost(postId);
    }
}