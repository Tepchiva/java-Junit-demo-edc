package aupp.junit;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Represents an IndustrialConsumer
 * Has all the properties of a BusinessConsumer including generating invoice
 * @author Lenovo
 * @author Shiladitya Munshi
 * @since 27.08.2023
 * @version 1.0
 */
public class IndustrialConsumer extends BusinessConsumer{

	private String _industryType;
	
	public IndustrialConsumer(String _consumerName, String _consumerAddress, String _meterId, double _lastMeterReading,
			double _currentMeterReading, Date _lastMeterRaedingDate, Date _currentMeterReadingDate,
			String _businessType, String _industryType) {
		super(_consumerName, _consumerAddress, _meterId, _lastMeterReading, _currentMeterReading, _lastMeterRaedingDate,
				_currentMeterReadingDate, _businessType);
		this._industryType = _industryType;
	}

	/**
	 * @return the _industryType
	 */
	public String get_industryType() {
		return _industryType;
	}
	
	/**
	 * @return the Invoice object
	 */
	public Invoice generateInvoice()
	{
		double billedAmount = 0.0;
		double unitConsumed = super.get_currentMeterReading() - super.get_lastMeterReading();
		if(_commercialType.equals(Constants.GREEN_COMMERCIAL_TYPE))
		{
			if(unitConsumed <= Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
			{
				if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
					billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
				if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))  
					billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.MEDIUM_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
				if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
					billedAmount = (Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.HEAVY_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
			}else {
				double surplus = unitConsumed - Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
				if(surplus <= Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
				{
					if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
						billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
						(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
						billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
						(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
						billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS)* Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
				}else {
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));

						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = ((Constants.GREEN_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.GREEN_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.GREEN_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.GREEN_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.GREEN_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
				}
			}
		}else {
			if(_commercialType.equals(Constants.BLUE_COMMERCIAL_TYPE))
			{
				if(unitConsumed <= Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
				{
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT * unitConsumed;
				}else {
					double surplus = unitConsumed - Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
					if(surplus <= Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
					{
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					}else {
						
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
							(Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = ((Constants.BLUE_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.BLUE_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
								(Constants.BLUE_BUSINESS_SECOND_PHASE_COST_PER_UNIT * Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY) + 
								(Constants.BLUE_BUSINESS_BEYOND_SECOND_PHASE_COST_PER_UNIT * (surplus - Constants.BLUE_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY));
					}
				}
			}else {
				if(unitConsumed <= Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY)
				{
					if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
						billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
					if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
						billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
					if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
						billedAmount = (Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * unitConsumed;
				}else {
					double surplus = unitConsumed - Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY;
					if(surplus <= Constants.RED_BUSINESS_SECOND_PHASE_UNIT_BOUNDARY)
					{
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
					}else {
						if(_industryType.equals(Constants.SMALL_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.MEDIUM_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
							(Constants.RED_BUSINESS_SECOND_PHASE_COST_PER_UNIT * surplus);
						if(_industryType.equals(Constants.HEAVY_INDUSTRY_TYPE))
							billedAmount = ((Constants.RED_BUSINESS_FIRST_PHASE_COST_PER_UNIT + Constants.SMALL_INDUSTRY_ADDITIONAL_COST_PER_UNIT_OVER_BUSINESS) * Constants.RED_BUSINESS_FIRST_PHASE_UNIT_BOUNDARY) +
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
