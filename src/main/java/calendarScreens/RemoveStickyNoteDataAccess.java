package calendarScreens;

import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteDsGateway;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteRequestModel;

import java.io.File;

public class RemoveStickyNoteDataAccess implements RemoveStickyNoteDsGateway {

    /**
     * Deletes the sticky note's text file.
     * */
    @Override
    public void delete(RemoveStickyNoteRequestModel requestModel) {
        File fileName = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes/" + requestModel.getTitle() + ".txt");
        fileName.delete();
    }
}
