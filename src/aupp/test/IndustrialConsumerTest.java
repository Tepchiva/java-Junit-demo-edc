package aupp.test;

import aupp.junit.Constants;
import aupp.junit.IndustrialConsumer;
import aupp.junit.InvoiceGenerator;
import org.junit.Before;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IndustrialConsumerTest extends LogicalLayerTest {

    @Before
    public void setUp() throws ParseException {
        super.invoiceGenerators = new ArrayList<>(List.of(
                // Test small industry, amount should be: 1110
                new IndustrialConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "IC001",
                        0,
                        120,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.RED_COMMERCIAL_TYPE,
                        Constants.HEAVY_INDUSTRY_TYPE
                ),
                // Test small industry, amount should be: 437.5
                new IndustrialConsumer(
                        "Chiva Tep",
                        "Phnom Penh",
                        "IC001",
                        0,
                        75,
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-08-08"),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse("2023-09-08"),
                        Constants.BLUE_COMMERCIAL_TYPE,
                        Constants.SMALL_INDUSTRY_TYPE
                )
        ));
    }

    @Override
    public double calculateBillAmount(InvoiceGenerator invoiceGenerator) {

        IndustrialConsumer industrialConsumer = (IndustrialConsumer) invoiceGenerator;
        double unitConsumed = industrialConsumer.get_currentMeterReading() - industrialConsumer.get_lastMeterReading();

        String commercialType = industrialConsumer.get_commercialType();
        String industryType = industrialConsumer.get_industryType();

        double billedAmount = 0.0;

        // GREEN_COMMERCIAL_TYPE
        if(commercialType.equals(Constants.GREEN_COMMERCIAL_TYPE)) {

            // phase 1
            if(unitConsumed <= Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) {
                if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                    billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;

                if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                    billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;

                if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                    billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
            }
            else {
                double restUnit = unitConsumed - Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
                // phase 2
                if(restUnit <= Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) {

                    if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                    if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                    if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);
                }
                // phase 3
                else {

                    if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                    if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                    if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                        billedAmount =
                                ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                + ((Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
                }
            }
        }
        else {
            //BLUE_COMMERCIAL_TYPE
            if(commercialType.equals(Constants.BLUE_COMMERCIAL_TYPE)) {
                // phase 1
                if(unitConsumed <= Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) {
                    if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                        billedAmount = (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
                    if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                        billedAmount = (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
                    if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                        billedAmount = (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
                }
                else {
                    double restUnit = unitConsumed - Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
                    // phase 2
                    if(restUnit <= Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) {
                        if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                        if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                        if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);
                    }
                    // phase 3
                    else {
                        if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                        if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                        if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                            billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                            + ((Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                            + ((Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
                    }
                }
            }

            //RED_COMMERCIAL_TYPE
            else {
                // phase 1
                if(unitConsumed <= Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) {
                    if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                        billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;

                    if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                        billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;

                    if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                        billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
                }
                else {
                    double restUnit = unitConsumed - Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
                    // phase 2
                    if(restUnit <= Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) {

                        if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                        if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);

                        if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * restUnit);
                    }
                    // phase 3
                    else {
                        if(industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                        if(industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

                        if(industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
                            billedAmount =
                                    ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
                                    + ((Constants.RED_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * (restUnit - Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
                    }
                }
            }
        }

        return billedAmount;
    }
}
