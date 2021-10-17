package ch6.exception;

import ch6.stub.Invoice;
import ch6.stub.InvoiceFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SAPInvoiceSenderTest {

    private InvoiceFilter filter = mock(InvoiceFilter.class);
    private SAP sap = mock(SAP.class);
    private SAPInvoiceSender sender = new SAPInvoiceSender(filter, sap);

    @Test
    void returnFailedInvoices() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 25);
        Invoice steve = new Invoice("Steve", 48);

        List<Invoice> invoices = Arrays.asList(mauricio, frank, steve);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");
        doThrow(new SAPException()).when(sap).send(franksInvoice);

        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();
        assertThat(failedInvoices).containsExactly(frank);

        SapInvoice mauriciosInvoice = new SapInvoice("Mauricio", 20, date + "Ma");
        verify(sap).send(mauriciosInvoice);

        SapInvoice stevesInvoice = new SapInvoice("Steve", 48, date + "St");
        verify(sap).send(stevesInvoice);


    }




}
