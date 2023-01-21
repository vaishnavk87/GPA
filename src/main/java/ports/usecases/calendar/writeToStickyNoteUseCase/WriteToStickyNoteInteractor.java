package ports.usecases.calendar.writeToStickyNoteUseCase;

import entities.calendarSystem.StickyNoteFactory;
import entities.calendarSystem.StickyNoteInterface;

import java.io.IOException;

public class WriteToStickyNoteInteractor implements WriteToStickyNoteInputBoundary {
    final WriteToStickyNoteDsGateway writeToStickyNoteDsGateway;
    final WriteToStickyNoteOutputBoundary writeToStickyNoteOutputBoundary;
    final StickyNoteFactory stickyNoteFactory;
//    final CalendarFactory calendarFactory;

    public WriteToStickyNoteInteractor(WriteToStickyNoteDsGateway writeToStickyNoteDsGateway, StickyNoteFactory stickyNoteFactory, WriteToStickyNoteOutputBoundary writeToStickyNoteOutputBoundary) {
        this.writeToStickyNoteDsGateway = writeToStickyNoteDsGateway;
        this.stickyNoteFactory = stickyNoteFactory;
        this.writeToStickyNoteOutputBoundary = writeToStickyNoteOutputBoundary;
    }

    /**
     * Writes a sticky note, and adds it to the calendar.
     * */
    @Override
    public String[] execute(WriteToStickyNoteRequestModel requestModel) throws IOException {
        StickyNoteInterface stickyNote = stickyNoteFactory.create(requestModel.getTitle(), requestModel.getType(), requestModel.getLocation(), requestModel.getTime(), requestModel.getDotJots(), requestModel.getDates(), requestModel.getDaysOfWeekPostedOn());
        writeToStickyNoteDsGateway.save(requestModel);

        WriteToStickyNoteResponseModel stickyNoteResponseModel = new WriteToStickyNoteResponseModel(stickyNote);
        return writeToStickyNoteOutputBoundary.prepareSuccessView(stickyNoteResponseModel);
    }
}
