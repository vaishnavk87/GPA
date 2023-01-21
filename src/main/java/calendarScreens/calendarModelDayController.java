package calendarScreens;

import ports.usecases.calendar.day.calendarModelDayInputBoundary;

public class calendarModelDayController {
    final calendarModelDayInputBoundary userInput;
    public calendarModelDayController(calendarModelDayInputBoundary inputBoundary) {
        this.userInput = inputBoundary;
    }
}
