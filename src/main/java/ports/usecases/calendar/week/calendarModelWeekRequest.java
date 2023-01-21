package ports.usecases.calendar.week;

import java.time.LocalDate;

public class calendarModelWeekRequest {
    public LocalDate date;

    public calendarModelWeekRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }
}
