package ports.usecases.calendar.writeToStickyNoteUseCase;

public interface WriteToStickyNoteOutputBoundary {
    String[] prepareSuccessView(WriteToStickyNoteResponseModel writeToStickyNoteResponseModel);
}
