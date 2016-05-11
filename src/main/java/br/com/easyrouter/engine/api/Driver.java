package br.com.easyrouter.engine.api;

import java.util.Date;

public class Driver {

	private Person person;
	private Calendar calendar;
	private ExternalCode externalCode;
	private Date earliestStart;
	private Date latestEnd;
	private DistributionCenter currentDistributionCenter;
	
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}
	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	/**
	 * @return the externalCode
	 */
	public ExternalCode getExternalCode() {
		return externalCode;
	}
	/**
	 * @param externalCode the externalCode to set
	 */
	public void setExternalCode(ExternalCode externalCode) {
		this.externalCode = externalCode;
	}
	/**
	 * @return the earliestStart
	 */
	public Date getEarliestStart() {
		return earliestStart;
	}
	/**
	 * @param earliestStart the earliestStart to set
	 */
	public void setEarliestStart(Date earliestStart) {
		this.earliestStart = earliestStart;
	}
	/**
	 * @return the latestEnd
	 */
	public Date getLatestEnd() {
		return latestEnd;
	}
	/**
	 * @param latestEnd the latestEnd to set
	 */
	public void setLatestEnd(Date latestEnd) {
		this.latestEnd = latestEnd;
	}
	
	public DistributionCenter getCurrentDistributionCenter() {
		return currentDistributionCenter;
	}
	
	public void setCurrentDistributionCenter(DistributionCenter currentDistributionCenter) {
		this.currentDistributionCenter = currentDistributionCenter;
	}
	
}
