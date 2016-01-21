package br.com.easyrouter.engine.api;

import java.util.List;

public class Calendar {

	private List<Interval> intervals;

	/**
	 * @return the intervals
	 */
	public List<Interval> getIntervals() {
		return intervals;
	}

	/**
	 * @param intervals the intervals to set
	 */
	public void setIntervals(List<Interval> intervals) {
		this.intervals = intervals;
	}
	
}
