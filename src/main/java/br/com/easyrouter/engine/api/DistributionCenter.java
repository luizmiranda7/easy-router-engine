package br.com.easyrouter.engine.api;

public class DistributionCenter {
	
	private String name;
	private Integer prepareDuration;
	private ExternalCode routePointExternalCode;
	private Calendar calendar;
	private ExternalCode externalCode;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the prepareDuration
	 */
	public Integer getPrepareDuration() {
		return prepareDuration;
	}
	/**
	 * @param prepareDuration the prepareDuration to set
	 */
	public void setPrepareDuration(Integer prepareDuration) {
		this.prepareDuration = prepareDuration;
	}
	/**
	 * @return the routePointExternalCode
	 */
	public ExternalCode getRoutePointExternalCode() {
		return routePointExternalCode;
	}
	/**
	 * @param routePointExternalCode the routePointExternalCode to set
	 */
	public void setRoutePointExternalCode(ExternalCode routePointExternalCode) {
		this.routePointExternalCode = routePointExternalCode;
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
	
	
}
