package ch9.sql;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlIntegrationTestBase {

    private Connection connection;
    protected InvoiceDao dao;

    @BeforeEach
    void openConnectionAndCleanup() throws SQLException {

        connection = DriverManager.getConnection("jdbc:hsqldb:mem:book");

        /**
         * Create table if it doesn't exist. Only for the example here.
         */
        PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists invoice (name varchar(100), value double)");
        preparedStatement.execute();
        connection.commit();

        dao = new InvoiceDao(connection);

        /**
         * Let's clean up the table before the test runs.
         * That will avoid possible flaky tests.
         *
         * Note that doing a single 'truncate' here seems simple and enough for this exercise.
         * In large systems, you will probably want to encapsulate the 'reset database' logic
         * somewhere else. Or even make use of specific frameworks for that.
         */
        connection.prepareStatement("truncate table invoice").execute();
    }

    @AfterEach
    void close() throws SQLException {
        /**
         * Closing up the connection might also be something you do
         * at the end of each test.
         * Or maybe only at the end of the entire test suite, just to optimize.
         * (In practice, you should also use some connection pool, like C3P0,
         * to handle connections)
         */
        connection.close();
    }

}
