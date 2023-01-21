package calendarScreens;

import ports.usecases.calendar.month.calendarModelMonthInputBoundary;

public class calendarModelMonthController {
    final calendarModelMonthInputBoundary userInput;

    public calendarModelMonthController(calendarModelMonthInputBoundary inputBoundary) {
        this.userInput = inputBoundary;
    }
}
