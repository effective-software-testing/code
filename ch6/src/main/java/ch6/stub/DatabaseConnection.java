package ch6.stub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a very naive database connection class.
 * In real life, you should make use of a decent database API,
 * such as Spring Data or Hibernate.
 */
public class DatabaseConnection {

    private static Connection connection;

    public DatabaseConnection() {
        if(connection !=null) return;

        withSql(() -> {
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb.db", "SA", "");
            try (var preparedStatement = connection.prepareStatement("create table if not exists invoice (name varchar(100), value double)")) {
                preparedStatement.execute();
                connection.commit();
            }
            return null;
        });
    }

    public Connection getConnection() {
        return connection;
    }

    public void resetDatabase() {
        withSql(() -> {
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb.db", "SA", "");
            try (var preparedStatement = connection.prepareStatement("delete from invoice")) {
                preparedStatement.execute();
                connection.commit();
            }
            return null;
        });
    }

    public interface SqlSupplier<T> {
        T doSql() throws SQLException;
    }
    public <T> T withSql(SqlSupplier<T> sqlSupplier) {
        try {
            return sqlSupplier.doSql();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        withSql( () -> {
            if (connection != null) {
                connection.close();
            }
            return null;
        });
        connection = null;
    }
}
