package aupp.test;

import aupp.junit.BusinessConsumer;
import aupp.junit.Constants;
import aupp.junit.InvoiceGenerator;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessConsumerNegativeTest extends BusinessConsumerTest {

    @Before
    public void setUp() throws ParseException {
        super.invoiceGenerators = new ArrayList<>(List.of(
                new BusinessConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "BC001",
                        49,
                        0,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        Constants.BLUE_COMMERCIAL_TYPE
                )
        ));
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateInvoice() {
        invoiceGenerators.forEach(InvoiceGenerator::generateInvoice);
    }

    @Override
    @Test(expected = NullPointerException.class)
    public void testCalculateBillAmount() {
        invoiceGenerators.forEach(invoiceGenerator -> invoiceGenerator.generateInvoice().get_billAmount());
    }

    @Override
    @Test(expected = NullPointerException.class)
    public void testCalculateAvgSpendingPerDay() {
        invoiceGenerators.forEach(invoiceGenerator -> invoiceGenerator.generateInvoice().get_avgSpendingPerDay());
    }

    @Override
    @Test(expected = NullPointerException.class)
    public void testCalculatePeriodInDays() {
        invoiceGenerators.forEach(invoiceGenerator -> invoiceGenerator.generateInvoice().get_billPeriodInDays());
    }
}
