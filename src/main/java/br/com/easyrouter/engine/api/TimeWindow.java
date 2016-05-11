package br.com.easyrouter.engine.api;

import java.util.Date;

/**
 * Created by luiz.miranda on 04/05/16.
 */
public class TimeWindow {

    private Date start;
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
