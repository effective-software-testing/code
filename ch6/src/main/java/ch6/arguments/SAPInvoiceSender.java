package ch6.arguments;

import ch6.stub.Invoice;
import ch6.stub.InvoiceFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SAPInvoiceSender {

    private final InvoiceFilter filter;
    private final SAP sap;

    public SAPInvoiceSender(InvoiceFilter filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    public void sendLowValuedInvoices() {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        for(Invoice invoice : lowValuedInvoices) {
            String customer = invoice.getCustomer();
            int value = invoice.getValue();
            String sapId = generateId(invoice);

            SapInvoice sapInvoice = new SapInvoice(customer, value, sapId);
            sap.send(sapInvoice);
        }
    }

    private String generateId(Invoice invoice) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String customer = invoice.getCustomer();
        return date + (customer.length()>=2 ? customer.substring(0,2) : "X");
    }
}
