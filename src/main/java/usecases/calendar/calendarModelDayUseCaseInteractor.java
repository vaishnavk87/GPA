package usecases.calendar;

import ports.usecases.calendar.day.calendarModelDayInputBoundary;
import ports.usecases.calendar.day.calendarModelDayOutputBoundary;
import ports.usecases.calendar.day.calendarModelDayRequest;
import ports.usecases.calendar.day.calendarModelDayResponse;

import java.time.LocalDate;

public class calendarModelDayUseCaseInteractor implements calendarModelDayInputBoundary {
    final calendarModelDayOutputBoundary dayOutput;

    public calendarModelDayUseCaseInteractor(calendarModelDayOutputBoundary dayOutput) {
        this.dayOutput = dayOutput;
    }

    /**
     * Takes calendarModelDayRequest and converts data into single day
     *
     * @param calendarDayRequest date (year-month-date all in terms of numbers)
     * @return date as LocalDate
     */
    @Override
    public calendarModelDayResponse execute(calendarModelDayRequest calendarDayRequest) {
        LocalDate day = calendarDayRequest.getDate();

        calendarModelDayResponse dayData = new calendarModelDayResponse(day);
        return dayOutput.prepareSuccessView(dayData);
    }
}
