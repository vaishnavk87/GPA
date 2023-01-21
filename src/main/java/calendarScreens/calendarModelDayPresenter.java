package calendarScreens;

import ports.usecases.calendar.day.calendarModelDayOutputBoundary;
import ports.usecases.calendar.day.calendarModelDayResponse;

public class calendarModelDayPresenter implements calendarModelDayOutputBoundary {
    @Override
    public calendarModelDayResponse prepareSuccessView(calendarModelDayResponse day) {
        return day;
    }
}
