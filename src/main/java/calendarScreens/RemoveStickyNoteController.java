package calendarScreens;

import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteInputBoundary;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteRequestModel;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteResponseModel;

public class RemoveStickyNoteController {
    final RemoveStickyNoteInputBoundary userInput;

    public RemoveStickyNoteController(RemoveStickyNoteInputBoundary inputBoundary) {
        this.userInput = inputBoundary;
    }

    RemoveStickyNoteResponseModel delete(String title) {
        RemoveStickyNoteRequestModel requestModel = new RemoveStickyNoteRequestModel(title);
        //calls on the interactor
        return userInput.execute(requestModel);
    }
}
