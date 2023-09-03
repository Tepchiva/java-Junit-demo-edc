package aupp.test;

import aupp.junit.Constants;
import aupp.junit.HouseholdConsumer;
import aupp.junit.InvoiceGenerator;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HouseholdConsumerNegativeTest extends HouseholdConsumerTest {

    @Before
    public void setUp() throws ParseException {

        super.invoiceGenerators = new ArrayList<>(List.of(
                new HouseholdConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "HC001",
                        16d,
                        13d,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        Constants.MECHANICAL_METER_TYPE
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
