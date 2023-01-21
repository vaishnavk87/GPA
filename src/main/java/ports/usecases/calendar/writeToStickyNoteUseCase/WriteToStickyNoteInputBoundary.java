package ports.usecases.calendar.writeToStickyNoteUseCase;

import java.io.IOException;

public interface WriteToStickyNoteInputBoundary {
    String[] execute(WriteToStickyNoteRequestModel requestModel) throws IOException;
}
