package org.example.dao.daoImpl;

import org.example.config.DBConnection;
import org.example.dao.CommentDao;
import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final Connection connection = DBConnection.getConnection();

    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS comments (
                    id SERIAL PRIMARY KEY,
                    text TEXT NOT NULL,
                    comment_date DATE NOT NULL,
                    post_id INT REFERENCES posts(id),
                    user_id INT REFERENCES users(id)
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'comments' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS comments";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'comments' dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanTable() {
        String sql = "TRUNCATE TABLE comments";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'comments' truncated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveComment(Comment comment) {
        String sql = """
                INSERT INTO comments (text, comment_date, post_id, user_id)
                VALUES (?, ?, ?, ?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comment.getText());
            statement.setDate(2, Date.valueOf(comment.getCommentDate()));
            statement.setLong(3, comment.getPostId().getId());
            statement.setLong(4, comment.getUserId().getId());
            statement.executeUpdate();
            System.out.println("Comment saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());

                Post post = new Post();
                post.setId(resultSet.getLong("post_id"));
                comment.setPostId(post);

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                comment.setUserId(user);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());

                Post post = new Post();
                post.setId(resultSet.getLong("post_id"));
                comment.setPostId(post);

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                comment.setUserId(user);

                return comment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateComment(Long id, Comment newComment) {
        String sql = "UPDATE comments SET text = ?, comment_date = ?, post_id = ?, user_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newComment.getText());
            preparedStatement.setDate(2, Date.valueOf(newComment.getCommentDate()));
            preparedStatement.setLong(3, newComment.getPostId().getId());
            preparedStatement.setLong(4, newComment.getUserId().getId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Comment updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCommentById(Long id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Comment deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        List<Comment> comments = new ArrayList<>();
        String sql = """
                SELECT * FROM comments
                WHERE post_id = ?
                ORDER BY comment_date DESC
                LIMIT ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postId);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());

                Post post = new Post();
                post.setId(resultSet.getLong("post_id"));
                comment.setPostId(post);

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                comment.setUserId(user);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Long countCommentsByPost(Long postId) {
        String sql = "SELECT COUNT(*) FROM comments WHERE post_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
