package br.com.easyrouter.engine.api;

import java.util.List;

public class Calendar {

	private List<TimeWindow> timeWindows;

	public List<TimeWindow> getTimeWindows() {
		return timeWindows;
	}

	public void setTimeWindows(List<TimeWindow> timeWindows) {
		this.timeWindows = timeWindows;
	}

}
