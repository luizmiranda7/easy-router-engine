package br.com.easyrouter.engine.api;

import java.util.Date;

public class Interval {

	private Date initialDate;
	private Date finalDate;
	
	/**
	 * @return the initialDate
	 */
	Date getInitialDate() {
		return initialDate;
	}
	/**
	 * @param initialDate the initialDate to set
	 */
	void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	/**
	 * @return the finalDate
	 */
	Date getFinalDate() {
		return finalDate;
	}
	/**
	 * @param finalDate the finalDate to set
	 */
	void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
}
