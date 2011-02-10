package pl.michalorman.kvmapper.core.fixture;

import java.util.Date;

/**
* @author Michal Orman
* @version 1.0
*/
public class WithDate {
    private Date date;

    public WithDate() {
    }

    public WithDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
