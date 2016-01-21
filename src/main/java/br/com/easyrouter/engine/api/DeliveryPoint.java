package br.com.easyrouter.engine.api;

public class DeliveryPoint {

	private String name;
	private Long deliveryDuration;
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
	 * @return the deliveryDuration
	 */
	public Long getDeliveryDuration() {
		return deliveryDuration;
	}
	/**
	 * @param deliveryDuration the deliveryDuration to set
	 */
	public void setDeliveryDuration(Long deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
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
	
}
