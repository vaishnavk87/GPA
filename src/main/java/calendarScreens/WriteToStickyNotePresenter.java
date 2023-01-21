package calendarScreens;

import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteOutputBoundary;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteResponseModel;

public class WriteToStickyNotePresenter implements WriteToStickyNoteOutputBoundary {

    @Override
    public String[] prepareSuccessView(WriteToStickyNoteResponseModel responseModel) {
        String[] formattedData = new String[7];
        formattedData[0] = "Title: " + responseModel.getStickyNote().getTitle();
        formattedData[1] = "Type: " + responseModel.getStickyNote().getType();
        formattedData[2] = "Location: " + responseModel.getStickyNote().getLocation();
        formattedData[3] = "Time: " + responseModel.getStickyNote().getTime();

        StringBuilder tempDates = new StringBuilder();
        for (int i = 0; i < responseModel.getStickyNote().getDates().length - 1; i++) {
            tempDates.append(responseModel.getStickyNote().getDates()[i].toString()).append(";");
        }
        tempDates.append(responseModel.getStickyNote().getDates()[responseModel.getStickyNote().getDates().length - 1].toString());

        formattedData[4] = "Dates: " + tempDates;

        StringBuilder tempDaysOfWeek = new StringBuilder();
        for (int i = 0; i < responseModel.getStickyNote().getDaysOfWeekToPostOn().length - 1; i++) {
            tempDaysOfWeek.append(responseModel.getStickyNote().getDaysOfWeekToPostOn()[i].toString()).append(";");
        }
        tempDaysOfWeek.append(responseModel.getStickyNote().getDaysOfWeekToPostOn()[responseModel.getStickyNote().getDaysOfWeekToPostOn().length - 1].toString());

        formattedData[5] = "Days of week: " + tempDaysOfWeek;
        formattedData[6] = responseModel.getStickyNote().getDotJots();
        return formattedData;
    }
}
