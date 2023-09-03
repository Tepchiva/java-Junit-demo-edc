package aupp.test;

import aupp.junit.*;
import org.junit.Before;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HouseholdConsumerTest extends LogicalLayerTest {

    @Before
    public void setUp() throws ParseException {

        super.invoiceGenerators = new ArrayList<>(List.of(
                new HouseholdConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "HC001",
                        0d,
                        10d,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.MECHANICAL_METER_TYPE
                ),
                new HouseholdConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "HC001",
                        0d,
                        101d,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.DIGITAL_METER_TYPE
                )
        ));
    }

    @Override
    public double calculateBillAmount(InvoiceGenerator invoiceGenerator) {

        HouseholdConsumer householdConsumer = (HouseholdConsumer) invoiceGenerator;

        double unitConsumed = householdConsumer.get_currentMeterReading() - householdConsumer.get_lastMeterReading();

        double billedAmount = unitConsumed <= Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY ? unitConsumed * Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_COST_PER_UNIT :
                (Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY * Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_COST_PER_UNIT) + ((unitConsumed - Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY) * Constants.MECHANICAL_HOUSEHOLD_BEYOND_FIRST_PHASE_COST_PER_UNIT);

        if(householdConsumer.get_meterType().equals(Constants.DIGITAL_METER_TYPE))
            billedAmount = billedAmount * (Constants.DIGITAL_HOUSEHOLD_REBATE_PERCENTAGE / 100);

        return billedAmount;
    }
}
