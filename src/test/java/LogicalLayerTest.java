import java.util.List;
import java.util.concurrent.TimeUnit;

import aupp.junit.Invoice;
import aupp.junit.InvoiceGenerator;
import org.junit.Test;

import aupp.junit.Consumer;

import static junit.framework.Assert.*;

public abstract class LogicalLayerTest {
    List<InvoiceGenerator> invoiceGenerators;

    public abstract double calculateBillAmount(InvoiceGenerator invoiceGenerator);

    @Test
    public void testGenerateInvoice() {
        invoiceGenerators.forEach(invoiceGenerator -> {
            Invoice invoice = invoiceGenerator.generateInvoice();
            Consumer consumer = (Consumer) invoiceGenerator;

            assertNotNull("Generate invoice is not null", invoice);
            assertEquals("Consumer name",consumer.get_consumerName(), invoice.get_consumerName());
        });
    }

    @Test
    public void testCalculateBillAmount() {
        invoiceGenerators.forEach(invoiceGenerator -> {
            assertTrue("Bill amount is more than 0", invoiceGenerator.generateInvoice().get_billAmount() > 0);
            assertEquals("Bill amount is", this.calculateBillAmount(invoiceGenerator), invoiceGenerator.generateInvoice().get_billAmount());
        });
    }

    @Test
    public void testCalculateAvgSpendingPerDay() {
        invoiceGenerators.forEach(invoiceGenerator -> {
            Consumer consumer = (Consumer) invoiceGenerator;
            Invoice invoice = invoiceGenerator.generateInvoice();

            assertTrue("Average spending per day amount is more than 0", invoice.get_billAmount() > 0);

            long diffInMillis = consumer.get_currentMeterReadingDate().getTime() - consumer.get_lastMeterRaedingDate().getTime();
            long periodDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            assertEquals("Average spend per day", this.calculateBillAmount(invoiceGenerator) / periodDays, invoice.get_avgSpendingPerDay());
        });
    }

    @Test
    public void testCalculatePeriodInDays() {
        invoiceGenerators.forEach(invoiceGenerator -> {
            Consumer consumer = (Consumer) invoiceGenerator;
            long diffInMillies = consumer.get_currentMeterReadingDate().getTime() - consumer.get_lastMeterRaedingDate().getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            assertTrue("Bill period in days is more than 0", diffInDays > 0);
            assertEquals("Bill period in days", diffInDays, invoiceGenerator.generateInvoice().get_billPeriodInDays());
        });

    }
}
