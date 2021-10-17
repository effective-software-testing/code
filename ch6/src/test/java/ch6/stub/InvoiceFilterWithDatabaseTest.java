package ch6.stub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceFilterWithDatabaseTest {
    private IssuedInvoices invoices;
    private DatabaseConnection dbConnection;

    @BeforeEach
    public void open() {
        dbConnection = new DatabaseConnection();
        invoices = new IssuedInvoices(dbConnection);

        // we need to clean up all the tables,
        // to make sure old data doesn't interfere with the test.
        dbConnection.resetDatabase();
    }

    @AfterEach
    public void close() {
        if (dbConnection != null) dbConnection.close();
    }

    @Test
    void filterInvoices() {
        final var mauricio = new Invoice("Mauricio", 20);
        final var steve = new Invoice("Steve", 99);
        final var frank = new Invoice("Frank", 100);

        // really saves the invoice to the database...
        // this is no good.
        invoices.save(mauricio);
        invoices.save(steve);
        invoices.save(frank);

        final InvoiceFilterWithDatabase filter = new InvoiceFilterWithDatabase();

        assertThat(filter.lowValueInvoices())
                .containsExactlyInAnyOrder(mauricio, steve);
    }
}