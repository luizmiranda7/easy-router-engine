package br.com.easyrouter.engine.api;

public class DirectionLeg {
	
	private Double distance;
	private Long duration;
	private ExternalCode initialPoint;
	private ExternalCode finalPoint;
	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 * @return the initialPoint
	 */
	public ExternalCode getInitialPoint() {
		return initialPoint;
	}
	/**
	 * @param initialPoint the initialPoint to set
	 */
	public void setInitialPoint(ExternalCode initialPoint) {
		this.initialPoint = initialPoint;
	}
	/**
	 * @return the finalPoint
	 */
	public ExternalCode getFinalPoint() {
		return finalPoint;
	}
	/**
	 * @param finalPoint the finalPoint to set
	 */
	public void setFinalPoint(ExternalCode finalPoint) {
		this.finalPoint = finalPoint;
	}
	
	

}
