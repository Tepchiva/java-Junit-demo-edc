package aupp.junit;

import java.util.Date;

/**
 * There is no physical existence of Consumer as such 
 * Hence it has been designed as abstract class - we don't need any object of this class
 * @author Shiladitya Munshi
 * @since 27.08.2023
 * @version 1.0
 *
 */
public abstract class Consumer {
	private String _consumerName;
	private String _consumerAddress;
	private String _meterId;
	private double _lastMeterReading;
	private double _currentMeterReading;
	private Date _lastMeterRaedingDate;
	private Date _currentMeterReadingDate;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param _consumerName
	 * @param _consumerAddress
	 * @param _meterId
	 * @param _lastMeterReading
	 * @param _currentMeterReading
	 * @param _lastMeterRaedingDate
	 * @param _currentMeterReadingDate
	 */
	public Consumer(String _consumerName, String _consumerAddress, String _meterId, double _lastMeterReading,
			double _currentMeterReading, Date _lastMeterRaedingDate, Date _currentMeterReadingDate) {
		super();
		this._consumerName = _consumerName;
		this._consumerAddress = _consumerAddress;
		this._meterId = _meterId;
		this._lastMeterReading = _lastMeterReading;
		this._currentMeterReading = _currentMeterReading;
		this._lastMeterRaedingDate = _lastMeterRaedingDate;
		this._currentMeterReadingDate = _currentMeterReadingDate;
	}

	/**
	 * @return the _consumerName
	 */
	public String get_consumerName() {
		return _consumerName;
	}

	/**
	 * @return the _consumerAddress
	 */
	public String get_consumerAddress() {
		return _consumerAddress;
	}

	/**
	 * @return the _meterId
	 */
	public String get_meterId() {
		return _meterId;
	}

	/**
	 * @return the _lastMeterReading
	 */
	public double get_lastMeterReading() {
		return _lastMeterReading;
	}

	/**
	 * @return the _currentMeterReading
	 */
	public double get_currentMeterReading() {
		return _currentMeterReading;
	}

	/**
	 * @return the _lastMeterRaedingDate
	 */
	public Date get_lastMeterRaedingDate() {
		return _lastMeterRaedingDate;
	}

	/**
	 * @return the _currentMeterReadingDate
	 */
	public Date get_currentMeterReadingDate() {
		return _currentMeterReadingDate;
	}
	
	
	
	
	
	
	
}
