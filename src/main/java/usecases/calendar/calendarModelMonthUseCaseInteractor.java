package usecases.calendar;

import ports.usecases.calendar.month.calendarModelMonthInputBoundary;
import ports.usecases.calendar.month.calendarModelMonthOutputBoundary;
import ports.usecases.calendar.month.calendarModelRequest;
import ports.usecases.calendar.month.calendarModelResponseMonth;

import java.time.LocalDate;

public class calendarModelMonthUseCaseInteractor implements calendarModelMonthInputBoundary {
    final calendarModelMonthOutputBoundary monthOutput;

    public calendarModelMonthUseCaseInteractor(calendarModelMonthOutputBoundary monthOutput) {
        this.monthOutput = monthOutput;
    }

    /**
     * Takes calenderModelRequest and converts date into array of dates in month
     *
     * @param calendarMonthRequest represents date (year-month-date in numbers)
     * @return array of list consisting days in month
     */
    @Override
    public calendarModelResponseMonth execute(calendarModelRequest calendarMonthRequest) {
        LocalDate[] monthDays = new LocalDate[42];
        LocalDate firstDay = LocalDate.of(calendarMonthRequest.getDate().getYear(),
                calendarMonthRequest.getDate().getMonth().getValue(), 1);
        int week = (firstDay.getDayOfWeek().getValue()) % 7;

        LocalDate temporary = firstDay;
        for (int i = week; i < monthDays.length; i++) {
            monthDays[i] = temporary;
            temporary = temporary.plusDays(1);
            if (temporary.getMonthValue() != firstDay.getMonthValue()) {
                i = 42;
            }
        }
        calendarModelResponseMonth monthData = new calendarModelResponseMonth(monthDays);
        return monthOutput.prepareSuccessView(monthData);
    }
}
