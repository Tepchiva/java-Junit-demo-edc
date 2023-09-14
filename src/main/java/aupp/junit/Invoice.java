package aupp.junit;

/**
 * This class represents an invoice
 * @author Shiladitya Munshi
 *
 */
public class Invoice {
	private String _consumerName;
	private String _consumerMeterId;
	private int _billPeriodInDays;
	private double _billAmount;
	private double _avgSpendingPerDay;
	
	public Invoice(String _consumerName, String _consumerMeterId, int _billPeriodInDays, double _billAmount,
			double _avgSpendingPerDay) {
		super();
		this._consumerName = _consumerName;
		this._consumerMeterId = _consumerMeterId;
		this._billPeriodInDays = _billPeriodInDays;
		this._billAmount = _billAmount;
		this._avgSpendingPerDay = _avgSpendingPerDay;
	}

	/**
	 * @return the _consumerName
	 */
	public String get_consumerName() {
		return _consumerName;
	}

	/**
	 * @return the _consumerMeterId
	 */
	public String get_consumerMeterId() {
		return _consumerMeterId;
	}

	/**
	 * @return the _billPeriodInDays
	 */
	public int get_billPeriodInDays() {
		return _billPeriodInDays;
	}

	/**
	 * @return the _billAmount
	 */
	public double get_billAmount() {
		return _billAmount;
	}

	/**
	 * @return the _avgSpendingPerDay
	 */
	public double get_avgSpendingPerDay() {
		return _avgSpendingPerDay;
	}
	
	
	
	 
}
