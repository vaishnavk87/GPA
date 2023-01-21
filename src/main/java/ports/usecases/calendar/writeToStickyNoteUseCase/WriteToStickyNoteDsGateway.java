package ports.usecases.calendar.writeToStickyNoteUseCase;

import java.io.IOException;

public interface WriteToStickyNoteDsGateway {
    void save(WriteToStickyNoteRequestModel requestModel) throws IOException;
}
