package calendarScreens;

import ports.usecases.calendar.week.calendarModelWeekInputBoundary;

public class calendarModelWeekController {
    final calendarModelWeekInputBoundary userInput;

    public calendarModelWeekController(calendarModelWeekInputBoundary inputBoundary) {
        this.userInput = inputBoundary;
    }
}
