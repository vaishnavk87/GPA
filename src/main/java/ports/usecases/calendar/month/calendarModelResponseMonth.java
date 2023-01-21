package ports.usecases.calendar.month;

import java.time.LocalDate;

public class calendarModelResponseMonth {
    LocalDate[] listOfMonthDays;

    public calendarModelResponseMonth(LocalDate[] listOfMonthDays) {
        this.listOfMonthDays = listOfMonthDays;
    }

    public LocalDate[] getListOfMonthDays() {
        return listOfMonthDays;
    }

    public void setListOfMonthDays(LocalDate[] listOfMonthDays) {
        this.listOfMonthDays = listOfMonthDays;
    }
}
