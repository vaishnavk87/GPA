package calendarScreens;

import ports.usecases.calendar.week.calendarModelWeekOutputBoundary;
import ports.usecases.calendar.week.calendarModelWeekResponse;

public class calendarModelWeekPresenter implements calendarModelWeekOutputBoundary {
    @Override
    public calendarModelWeekResponse prepareSuccessView(calendarModelWeekResponse week) {
        return week;
    }
}
