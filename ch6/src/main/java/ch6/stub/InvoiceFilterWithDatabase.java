package ch6.stub;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class InvoiceFilterWithDatabase {

    public List<Invoice> lowValueInvoices() {
        DatabaseConnection connection = new DatabaseConnection();
        IssuedInvoices issuedInvoices = new IssuedInvoices(connection);

        try {
            List<Invoice> all = issuedInvoices.all();

            return all.stream()
                    .filter(invoice -> invoice.getValue() < 100)
                    .collect(toList());
        } finally {
            connection.close();
        }
    }
}
