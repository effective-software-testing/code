package ch6.arguments;

import ch6.stub.Invoice;
import ch6.stub.InvoiceFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

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

    @ParameterizedTest
    @CsvSource({
            "Mauricio,Ma",
            "M,X"}
    )
    void sendToSapWithTheGeneratedId(String customer, String initialId) {
        Invoice mauricio = new Invoice(customer, 20);

        List<Invoice> invoices = Arrays.asList(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        ArgumentCaptor<SapInvoice> captor = ArgumentCaptor.forClass(SapInvoice.class);
        verify(sap).send(captor.capture());

        SapInvoice generatedSapInvoice = captor.getValue();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        assertThat(generatedSapInvoice).isEqualTo(new SapInvoice(customer, 20, date + initialId));
    }


    @Test
    void oldExample() {
        Invoice mauricio = new Invoice("Mauricio", 20);

        List<Invoice> invoices = Arrays.asList(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        verify(sap).send(any(SapInvoice.class));
    }


}
