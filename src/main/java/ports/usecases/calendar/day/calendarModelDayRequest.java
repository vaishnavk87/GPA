package ports.usecases.calendar.day;

import java.time.LocalDate;

public class calendarModelDayRequest {
    public LocalDate date;

    public calendarModelDayRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }
}
