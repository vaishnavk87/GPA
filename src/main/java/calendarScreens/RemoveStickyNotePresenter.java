package calendarScreens;

import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteOutputBoundary;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteRequestModel;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteResponseModel;

import java.io.File;

public class RemoveStickyNotePresenter implements RemoveStickyNoteOutputBoundary {

    @Override
    public RemoveStickyNoteResponseModel prepareSuccessView(RemoveStickyNoteRequestModel requestModel) {
        File fileName = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes/" + requestModel.getTitle() + ".txt");
        RemoveStickyNoteResponseModel responseModel = new RemoveStickyNoteResponseModel(fileName.exists());
        return responseModel;
    }
}
