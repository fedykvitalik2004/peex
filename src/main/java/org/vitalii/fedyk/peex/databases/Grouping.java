package org.vitalii.fedyk.peex.databases;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.vitalii.fedyk.peex.databases.responses.PostResponse;
import org.vitalii.fedyk.peex.databases.responses.UserResponse;

import java.sql.*;
import java.util.List;

import static org.vitalii.fedyk.peex.databases.Grouping.Colour.ANSI_GREEN;
import static org.vitalii.fedyk.peex.databases.Grouping.Colour.ANSI_RESET;

public class Grouping {
    private static final String url = "jdbc:postgresql://localhost:5432/db";
    public static final String user = "vitalii";
    public static final String password = "260604";
    private static final String api_key = System.getenv("api_key");

    @AllArgsConstructor
    enum Colour {
        ANSI_RESET("\u001B[0m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_RED("\u001B[31m");

        private final String number;
    }

    @SneakyThrows
    private static void createTables(final Statement statement) {
        statement.execute("""
                 CREATE TABLE IF NOT EXISTS users (
                     id INT PRIMARY KEY,
                     username VARCHAR(30) NOT NULL UNIQUE,
                     email varchar(100) NOT NULL UNIQUE,
                     password TEXT NOT NULL,
                     created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                 );
                """);
        statement.execute("""
                CREATE TABLE IF NOT EXISTS Posts (
                    id INT PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    content TEXT NOT NULL,
                    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    user_id INT NOT NULL,
                    CONSTRAINT FK_posts_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
                );
                """);
    }

    private static void insertData(final Connection connection, final int userCount, int postAmount) {
        final MockarooDataRetriever mockarooDataRetriever = new MockarooDataRetriever(api_key);
        List<Integer> userIds = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?);")) {
            final List<UserResponse> userResponses = mockarooDataRetriever.generateUsers(userCount);
            userIds = userResponses.stream()
                    .map(o -> Integer.parseInt(o.getId()))
                    .toList();
            for (UserResponse userResponse : userResponses) {
                preparedStatement.setInt(1, Integer.parseInt(userResponse.getId()));
                preparedStatement.setString(2, userResponse.getUsername());
                preparedStatement.setString(3, userResponse.getEmail());
                preparedStatement.setString(4, userResponse.getPassword());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        try (PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO posts (id, title, content, user_id) VALUES (?, ?, ?, ?);")) {
            final List<PostResponse> postResponses = mockarooDataRetriever.generatePosts(postAmount, userIds);
            for (PostResponse postResponse : postResponses) {
                preparedStatement.setInt(1, Integer.parseInt(postResponse.getId()));
                preparedStatement.setString(2, postResponse.getTitle());
                preparedStatement.setString(3, postResponse.getContent());
                preparedStatement.setInt(4, Integer.parseInt(postResponse.getUserId()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static void printNumberOfPostsPerUser(final Statement statement) {
        final ResultSet resultSet = statement.executeQuery("""
                SELECT u.username, COUNT(p.id) AS c
                FROM users AS u
                LEFT JOIN posts AS P ON p.user_id = u.id
                GROUP BY u.username
                HAVING COUNT(p.id) >= 1
                ORDER BY c DESC
                """);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2));
        }
    }

    @SneakyThrows
    private static void printAverageNumberPostOfUser(final Statement statement) {
        final ResultSet resultSet = statement.executeQuery("""
                SELECT (SELECT COUNT(*) FROM posts) / (SELECT COUNT(*) FROM users) AS average_number;
                """);
        resultSet.next();
        System.out.println(resultSet.getString(1));
    }

    @SneakyThrows
    private static void printGendersAmount(final Statement statement) {
        final ResultSet resultSet = statement.executeQuery("""
                SELECT gender, count(gender) as amount
                FROM users
                GROUP BY gender
                """);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "  " + resultSet.getInt(2));
        }
    }

    @SneakyThrows
    private static void deleteData(final Statement statement) {
        statement.executeUpdate("DELETE FROM users");
    }

    @SneakyThrows
    private static void createViewForRetrievingLastPostDetailsOfUsers(final Statement statement) {
        statement.executeUpdate("""
                CREATE OR REPLACE VIEW last_post_per_user AS
                SELECT u.id as user_id, u.username as username, lp.created as post_created
                FROM users AS u
                INNER JOIN (
                    SELECT user_id, MAX(created) as created
                    FROM posts
                    GROUP BY user_id
                ) as lp ON u.id = lp.user_id;
                """);
    }

    @SneakyThrows
    private static void printViewForRetrievingLastPostDetailsOfUsers(final Statement statement) {
        final ResultSet resultSet = statement.executeQuery("""
                SELECT user_id, username, post_created
                FROM last_post_per_user
                """);
        while (resultSet.next()) {
            System.out.println(resultSet.getLong(1) + "  " + resultSet.getString(2) + "  " +
                               resultSet.getTimestamp(3));
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        try (var connection = DriverManager.getConnection(url, user, password)) {
            System.out.println(ANSI_GREEN.number + "Connected to Database" + ANSI_RESET.number);
            try (Statement statement = connection.createStatement()) {
                createTables(statement);
                deleteData(statement);
                insertData(connection, 10, 30);
                System.out.println(ANSI_GREEN.number + "Data added" + ANSI_RESET.number);

                statement.executeUpdate("ALTER TABLE users ALTER COLUMN username TYPE VARCHAR(60)");
                statement.executeUpdate("ALTER TABLE users ALTER COLUMN username SET NOT NULL");
                statement.executeUpdate("ALTER TABLE users ADD COLUMN gender VARCHAR(1)");
                statement.executeUpdate("""
                        UPDATE users
                        SET gender = 'W'
                        WHERE id % 2 = 0
                        """);
                statement.executeUpdate("""
                        UPDATE users
                        SET gender = 'M'
                        WHERE id % 2 != 0
                        """);

                System.out.println("The amount of posts per user");
                printNumberOfPostsPerUser(statement);

                System.out.println("The average number of posts per user");
                printAverageNumberPostOfUser(statement);

                System.out.println("Gender amount");
                printGendersAmount(statement);

                statement.executeUpdate("ALTER TABLE users DROP COLUMN email");

                System.out.println(ANSI_GREEN.number + "Created a view for retrieving the last post details" +
                                   ANSI_RESET.number);
                createViewForRetrievingLastPostDetailsOfUsers(statement);

                System.out.println("Date of the last post created");
                printViewForRetrievingLastPostDetailsOfUsers(statement);
            }
        }
    }
}
