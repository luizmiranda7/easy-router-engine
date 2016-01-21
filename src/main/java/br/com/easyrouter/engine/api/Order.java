package br.com.easyrouter.engine.api;

import java.util.Date;

public class Order {
	
	private Integer priorityLevel;
	private Integer weight;
	private Integer volume;
	private Date deadline;
	private Double penalty;
	private OrderStatus status;
	
	private ExternalCode externalCode;
	private DeliveryPoint deliveryPoint;
	private DistributionCenter distributionCenter;
	/**
	 * @return the priorityLevel
	 */
	public Integer getPriorityLevel() {
		return priorityLevel;
	}
	/**
	 * @param priorityLevel the priorityLevel to set
	 */
	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
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
	 * @return the deliveryPoint
	 */
	public DeliveryPoint getDeliveryPoint() {
		return deliveryPoint;
	}
	/**
	 * @param deliveryPoint the deliveryPoint to set
	 */
	public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}
	/**
	 * @return the distributionCenter
	 */
	public DistributionCenter getDistributionCenter() {
		return distributionCenter;
	}
	/**
	 * @param distributionCenter the distributionCenter to set
	 */
	public void setDistributionCenter(DistributionCenter distributionCenter) {
		this.distributionCenter = distributionCenter;
	}
	/**
	 * @return the penalty
	 */
	public Double getPenalty() {
		return penalty;
	}
	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}
	/**
	 * @return the volume
	 */
	public Integer getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	public String getName() {
		return new StringBuilder(this.distributionCenter.getName())
				.append(" -> ")
				.append(this.deliveryPoint.getName())
				.append(" : ")
				.append(this.externalCode.toString())
				.toString();
	}
	
}
