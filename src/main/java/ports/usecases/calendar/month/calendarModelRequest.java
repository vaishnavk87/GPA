package ports.usecases.calendar.month;

import java.time.LocalDate;

public class calendarModelRequest {
    public LocalDate date;

    /**
     * @param date is a LocalDate representing a date
     */
    public calendarModelRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }
}
