package pl.michalorman.kvmapper.core.fixture;

import java.util.Date;

/**
* @author Michal Orman
* @version 1.0
*/
public class AnnotatedWithDate {
    private Date date;

    public AnnotatedWithDate() {
    }

    public AnnotatedWithDate(Date date) {
        this.date = date;
    }

    @pl.michalorman.kvmapper.core.annotation.DateFormat("yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    @pl.michalorman.kvmapper.core.annotation.DateFormat("yyyy-MM-dd")
    public void setDate(Date date) {
        this.date = date;
    }
}
