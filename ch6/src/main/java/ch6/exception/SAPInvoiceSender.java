package ch6.exception;

import ch6.stub.Invoice;
import ch6.stub.InvoiceFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SAPInvoiceSender {

    private final InvoiceFilter filter;
    private final SAP sap;

    public SAPInvoiceSender(InvoiceFilter filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    public List<Invoice> sendLowValuedInvoices() {
        List<Invoice> failedInvoices = new ArrayList<>();

        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        for(Invoice invoice : lowValuedInvoices) {
            String customer = invoice.getCustomer();
            int value = invoice.getValue();
            String sapId = generateId(invoice);

            SapInvoice sapInvoice = new SapInvoice(customer, value, sapId);
            try {
                sap.send(sapInvoice);
            } catch(SAPException e) {
                failedInvoices.add(invoice);
            }
        }

        return failedInvoices;
    }

    private String generateId(Invoice invoice) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String customer = invoice.getCustomer();
        return date + (customer.length()>=2 ? customer.substring(0,2) : "X");
    }
}
