package aupp.junit;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Represents a household consumer. It has all the proerties of a consumer and has to generate invoice
 * So it extends Consumer and implements InvoiceGenerator
 * @author Shiladitya Munshi
 * @since 27.08.2023
 * @version 1.0
 */
public class HouseholdConsumer extends Consumer implements InvoiceGenerator {

	private String _meterType;
	
	public HouseholdConsumer(String _consumerName, String _consumerAddress, String _meterId, double _lastMeterReading,
			double _currentMeterReading, Date _lastMeterRaedingDate, Date _currentMeterReadingDate, String _meterType) {
		super(_consumerName, _consumerAddress, _meterId, _lastMeterReading, _currentMeterReading, _lastMeterRaedingDate,
				_currentMeterReadingDate);
		this._meterType = _meterType;
	}

	@Override
	public Invoice generateInvoice() {
		double billedAmount = 0.0;
		double unitConsumed = super.get_currentMeterReading() - super.get_lastMeterReading();
		if(unitConsumed <= Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY) {
			billedAmount = Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
		}
		else {
			billedAmount = (Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_COST_PER_UNIT * Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY) +
					(Constants.MECHANICAL_HOUSEHOLD_BEYOND_FIRST_PHASE_COST_PER_UNIT * (unitConsumed - Constants.MECHANICAL_HOUSEHOLD_FIRST_PHASE_UNIT_BOUNDARY));
		}
		if(_meterType.equals(Constants.DIGITAL_METER_TYPE))
			billedAmount = billedAmount*(Constants.DIGITAL_HOUSEHOLD_REBATE_PERCENTAGE/100);

		long diffInMillies = Math.abs(super.get_currentMeterReadingDate().getTime() - super.get_lastMeterRaedingDate().getTime());
	    long dateDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    double avgSpendingPerDay = billedAmount / dateDiff;

	    Invoice invoice = new Invoice(super.get_consumerName(), super.get_meterId(), (int)dateDiff, billedAmount, avgSpendingPerDay);
		return invoice;
	}

	public String get_meterType() {
		return _meterType;
	}
}
