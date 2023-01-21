package calendarScreens;

import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteInputBoundary;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteRequestModel;

import java.io.IOException;
import java.time.*;

public class WriteToStickyNoteController {
    final WriteToStickyNoteInputBoundary userInput;

    public WriteToStickyNoteController(WriteToStickyNoteInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    String[] create(String title, String type, String location, String time, String dotJots, String dates, String daysOfWeek) throws IOException {
        String[] stringDates = dates.split(";");
        LocalDate[] listOfDates = new LocalDate[stringDates.length];
        for (int i = 0; i < listOfDates.length; i++) {
            //default date format: yyyy-mm-dd
            listOfDates[i] = LocalDate.parse(stringDates[i]);
        }
        String[] stringDaysOfWeek = daysOfWeek.split(";");
        DayOfWeek[] listOfDaysOfWeek = new DayOfWeek[stringDaysOfWeek.length];
        for (int j = 0; j < listOfDaysOfWeek.length; j++) {
            listOfDaysOfWeek[j] = DayOfWeek.valueOf(stringDaysOfWeek[j].toUpperCase());
        }
        WriteToStickyNoteRequestModel requestModel = new WriteToStickyNoteRequestModel(
                title, type, location, time, dotJots, listOfDates, listOfDaysOfWeek);

        return userInput.execute(requestModel);
    }
}
