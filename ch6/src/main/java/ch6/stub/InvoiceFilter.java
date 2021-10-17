package ch6.stub;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class InvoiceFilter {

    private final IssuedInvoices issuedInvoices;

    public InvoiceFilter(IssuedInvoices issuedInvoices) {
        this.issuedInvoices = issuedInvoices;
    }
    public List<Invoice> lowValueInvoices() {
        List<Invoice> all = issuedInvoices.all();

        return all.stream()
                .filter(invoice -> invoice.getValue() < 100)
                .collect(toList());
    }
}
