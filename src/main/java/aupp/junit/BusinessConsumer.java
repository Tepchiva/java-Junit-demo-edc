package aupp.junit;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Represents a Business Consumer
 * A business consumer has all the properties of a consumer and it has to generate an invoice
 * So this extends Consumer and implements Invoice Generator
 * 
 * @author Shiladitya Munshi
 * @since 27.08.2023
 * @version 1.0
 *
 */
public class BusinessConsumer extends Consumer implements InvoiceGenerator{

	public String _commercialType;
	
	public BusinessConsumer(String _consumerName, String _consumerAddress, String _meterId, double _lastMeterReading,
			double _currentMeterReading, Date _lastMeterRaedingDate, Date _currentMeterReadingDate, String _commercialType) {
		super(_consumerName, _consumerAddress, _meterId, _lastMeterReading, _currentMeterReading, _lastMeterRaedingDate,
				_currentMeterReadingDate);
		this._commercialType = _commercialType;
	}

	/**
	 * @return the _businessType
	 */
	public String get_commercialType() {
		return _commercialType;
	}

	@Override
	public Invoice generateInvoice() {
		double billedAmount = 0.0;
		double unitConsumed = super.get_currentMeterReading() - super.get_lastMeterReading();
		if(_commercialType.equals(Constants.GREEN_COMMERCIAL_TYPE))
		{
			if(unitConsumed <= Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
			{
				billedAmount = Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
			}else {
				double surplus = unitConsumed - Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
				if(surplus <= Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
				{
					billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
				}else {
					billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
				}
			}
		}else {
			if(_commercialType.equals(Constants.BLUE_COMMERCIAL_TYPE))
			{
				if(unitConsumed <= Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
				{
					billedAmount = Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
				}else {
					double surplus = unitConsumed - Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
					if(surplus <= Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
					{
						billedAmount = (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					}else {
						billedAmount = (Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
								(Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
					}
				}
			}else {
				if(unitConsumed <= Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
				{
					billedAmount = Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
				}else {
					double surplus = unitConsumed - Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
					if(surplus <= Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
					{
						billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					}else {
						billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
								(Constants.RED_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
					}
				}
			}
		}
		long diffInMillies = Math.abs(super.get_currentMeterReadingDate().getTime() - super.get_lastMeterRaedingDate().getTime());
	    long dateDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    double avgSpendingPerDay = billedAmount / dateDiff;
	    
	    Invoice invoice = new Invoice(super.get_consumerName(), super.get_meterId(), (int)dateDiff, billedAmount, avgSpendingPerDay);
		return invoice;
		
	}

	
}
