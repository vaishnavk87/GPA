package ports.usecases.calendar.removeStickyNoteUseCase;

public interface RemoveStickyNoteOutputBoundary {
    //RemoveStickyNoteResponseModel execute(RemoveStickyNoteRequestModel requestModel);
    RemoveStickyNoteResponseModel prepareSuccessView(RemoveStickyNoteRequestModel requestModel);
}
