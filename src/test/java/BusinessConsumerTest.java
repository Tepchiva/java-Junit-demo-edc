import aupp.junit.BusinessConsumer;
import aupp.junit.Constants;
import aupp.junit.InvoiceGenerator;
import org.junit.Before;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessConsumerTest extends LogicalLayerTest {

    @Before
    public void setUp() throws ParseException {
        super.invoiceGenerators = new ArrayList<>(List.of(
                new BusinessConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "BC001",
                        0,
                        49,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.BLUE_COMMERCIAL_TYPE
                ),
                new BusinessConsumer(
                        "Chiva Tep 2",
                        "Phnom Penh",
                        "BC002",
                        0,
                        51,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.BLUE_COMMERCIAL_TYPE
                )
                ,new BusinessConsumer(
                        "Chiva Tep 3",
                        "Phnom Penh",
                        "BC002",
                        0,
                        101,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.BLUE_COMMERCIAL_TYPE
                ),
                new BusinessConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "BC001",
                        0,
                        49,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.GREEN_COMMERCIAL_TYPE
                ),
                new BusinessConsumer(
                        "Chiva Tep 2",
                        "Phnom Penh",
                        "BC002",
                        0,
                        51,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.GREEN_COMMERCIAL_TYPE
                )
                ,new BusinessConsumer(
                        "Chiva Tep 3",
                        "Phnom Penh",
                        "BC002",
                        0,
                        101,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.GREEN_COMMERCIAL_TYPE
                ),
                new BusinessConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "BC001",
                        0,
                        49,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.RED_COMMERCIAL_TYPE
                ),
                new BusinessConsumer(
                        "Chiva Tep 2",
                        "Phnom Penh",
                        "BC002",
                        0,
                        51,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.RED_COMMERCIAL_TYPE
                )
                ,new BusinessConsumer(
                        "Chiva Tep 3",
                        "Phnom Penh",
                        "BC002",
                        0,
                        101,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.RED_COMMERCIAL_TYPE
                )
        ));


    }

    @Override
    public double calculateBillAmount(InvoiceGenerator invoiceGenerator) {

        BusinessConsumer businessConsumer = (BusinessConsumer) invoiceGenerator;
        double unitConsumed = businessConsumer.get_currentMeterReading() - businessConsumer.get_lastMeterReading();

        return switch (businessConsumer.get_commercialType()) {
            case Constants.GREEN_COMMERCIAL_TYPE -> {
                if(unitConsumed <= Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                    yield Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;

                else {
                    double restUnit = unitConsumed - Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;

                    if(restUnit <= Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                        yield (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * restUnit);

                    else {
                        restUnit = unitConsumed - (Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY + Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY);
                        yield (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + (Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * restUnit);
                    }
                }
            }
            case Constants.BLUE_COMMERCIAL_TYPE -> {
                if(unitConsumed <= Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                    yield Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;

                else {
                    double restUnit = unitConsumed - Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;

                    if(restUnit <= Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                        yield (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * restUnit);

                    else {
                        restUnit = unitConsumed - (Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY + Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY);
                        yield (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + (Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * restUnit);
                    }
                }
            }
            case Constants.RED_COMMERCIAL_TYPE -> {
                if(unitConsumed <= Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                    yield Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;

                else {
                    double restUnit = unitConsumed - Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;

                    if(restUnit <= Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                        yield (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * restUnit);

                    else {
                        restUnit = unitConsumed - (Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY + Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY);
                        yield (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) + (Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + (Constants.RED_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * restUnit);
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + businessConsumer.get_commercialType());
        };
    }
}
