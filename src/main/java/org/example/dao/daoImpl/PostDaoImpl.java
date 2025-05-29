package org.example.dao.daoImpl;

import org.example.config.DBConnection;
import org.example.dao.PostDao;
import org.example.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private final Connection connection = DBConnection.getConnection();

    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts (
                    id SERIAL PRIMARY KEY,
                    image VARCHAR(255) NOT NULL,
                    description VARCHAR(255) NOT NULL,
                    created_date DATE NOT NULL,
                    user_id INT REFERENCES users(id)
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'posts' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS posts";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'posts' dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanTable() {
        String sql = "TRUNCATE TABLE posts";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table 'posts' truncated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePost(Post post) {
        String sql = """
                INSERT INTO posts (image, description, created_date, user_id)
                VALUES (?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, post.getImage());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setDate(3, Date.valueOf(post.getCreatedDate()));
            preparedStatement.setLong(4, post.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("Post saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getPostById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        String sql = "UPDATE posts SET image = ?, description = ?, created_date = ?, user_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPost.getImage());
            preparedStatement.setString(2, newPost.getDescription());
            preparedStatement.setDate(3, Date.valueOf(newPost.getCreatedDate()));
            preparedStatement.setLong(4, newPost.getUserId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Post updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Post deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long countPostsByUser(Long id) {
        String sql = "SELECT COUNT(*) FROM posts WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
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
