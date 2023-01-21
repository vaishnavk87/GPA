package calendarScreens;

import ports.usecases.calendar.month.calendarModelMonthOutputBoundary;
import ports.usecases.calendar.month.calendarModelResponseMonth;

public class calendarModelMonthPresenter implements calendarModelMonthOutputBoundary {
    @Override
    public calendarModelResponseMonth prepareSuccessView(calendarModelResponseMonth month) {
        return month;
    }
}
