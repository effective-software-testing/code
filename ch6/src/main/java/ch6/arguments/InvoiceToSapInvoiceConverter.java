package ch6.arguments;

import ch6.stub.Invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceToSapInvoiceConverter {

    public SapInvoice convert(Invoice invoice) {
        String customer = invoice.getCustomer();
        int value = invoice.getValue();
        String sapId = generateId(invoice);

        SapInvoice sapInvoice = new SapInvoice(customer, value, sapId);
        return sapInvoice;
    }

    private String generateId(Invoice invoice) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String customer = invoice.getCustomer();
        return date + (customer.length()>=2 ? customer.substring(0,2) : "X");
    }
}
