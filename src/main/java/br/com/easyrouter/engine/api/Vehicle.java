package br.com.easyrouter.engine.api;

import java.util.Date;

public class Vehicle {
	
	private Double maxVelocity;
	private Double costPerDistance;
	private Double costPerTime;
	private Double totalWeight;
	private Double totalVolume;
	private Date endTime;
	private Integer axes;
	private String type;
	private ExternalCode externalCode;
	private Date earliestStart;
	private Date latestEnd;
	
	/**
	 * @return the totalWeight
	 */
	public Double getTotalWeight() {
		return totalWeight;
	}
	/**
	 * @param totalWeight the totalWeight to set
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	/**
	 * @return the totalVolume
	 */
	public Double getTotalVolume() {
		return totalVolume;
	}
	/**
	 * @param totalVolume the totalVolume to set
	 */
	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}
	/**
	 * @return the axes
	 */
	public Integer getAxes() {
		return axes;
	}
	/**
	 * @param axes the axes to set
	 */
	public void setAxes(Integer axes) {
		this.axes = axes;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	
	public Double getCostCoeficient(){
		return new Double((this.totalVolume * this.totalWeight * this.axes) / 3);
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the costPerTime
	 */
	public Double getCostPerTime() {
		return costPerTime;
	}
	/**
	 * @param costPerTime the costPerTime to set
	 */
	public void setCostPerTime(Double costPerTime) {
		this.costPerTime = costPerTime;
	}
	/**
	 * @return the costPerDistance
	 */
	public Double getCostPerDistance() {
		return costPerDistance;
	}
	/**
	 * @param costPerDistance the costPerDistance to set
	 */
	public void setCostPerDistance(Double costPerDistance) {
		this.costPerDistance = costPerDistance;
	}
	/**
	 * @return the maxVelocity
	 */
	public Double getMaxVelocity() {
		return maxVelocity;
	}
	/**
	 * @param maxVelocity the maxVelocity to set
	 */
	public void setMaxVelocity(Double maxVelocity) {
		this.maxVelocity = maxVelocity;
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

}
