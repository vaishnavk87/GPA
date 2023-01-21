package ports.usecases.calendar.writeToStickyNoteUseCase;

import java.time.*;

public class WriteToStickyNoteRequestModel {
    private String title;
    private String type;
    private String location;
    private String time;
    private String dotJots;
    private LocalDate[] dates;
    private DayOfWeek[] daysOfWeekPostedOn;

    public WriteToStickyNoteRequestModel(String title, String type, String location, String time, String dotJots, LocalDate[] dates, DayOfWeek[] daysOfWeekPostedOn) {
        this.title = title;
        this.type = type;
        this.location = location;
        this.time = time;
        this.dotJots = dotJots;
        this.dates = dates;
        this.daysOfWeekPostedOn = daysOfWeekPostedOn;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return type;
    }

    void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    public String getDotJots() {
        return dotJots;
    }

    void setDotJots(String dotJots) {
        this.dotJots = dotJots;
    }

    public LocalDate[] getDates() {
        return dates;
    }

    void setDates(LocalDate[] dates) {
        this.dates = dates;
    }

    public DayOfWeek[] getDaysOfWeekPostedOn() {
        return daysOfWeekPostedOn;
    }

    void setDaysOfWeekPostedOn(DayOfWeek[] daysOfWeekPostedOn) {
        this.daysOfWeekPostedOn = daysOfWeekPostedOn;
    }
}
