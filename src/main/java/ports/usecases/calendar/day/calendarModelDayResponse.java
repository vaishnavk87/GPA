package ports.usecases.calendar.day;

import java.time.LocalDate;

public class calendarModelDayResponse {
    LocalDate day;

    public calendarModelDayResponse(LocalDate day) {
        this.day = day;
    }

    public LocalDate day() {
        return day;
    }

    public void day(LocalDate day) {
        this.day = day;
    }
}
