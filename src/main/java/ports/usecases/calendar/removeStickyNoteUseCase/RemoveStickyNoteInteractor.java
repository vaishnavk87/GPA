package ports.usecases.calendar.removeStickyNoteUseCase;

public class RemoveStickyNoteInteractor implements RemoveStickyNoteInputBoundary {
    private RemoveStickyNoteDsGateway dataAccessGateway;
    private RemoveStickyNoteOutputBoundary removeStickyNoteremoveStickyNoteOutputBoundary;

    public RemoveStickyNoteInteractor(RemoveStickyNoteDsGateway dataAccessGateway, RemoveStickyNoteOutputBoundary removeStickyNoteOutputBoundary) {
        this.dataAccessGateway = dataAccessGateway;
        this.removeStickyNoteremoveStickyNoteOutputBoundary = removeStickyNoteOutputBoundary;
    }

    @Override
    public RemoveStickyNoteResponseModel execute(RemoveStickyNoteRequestModel requestModel) {
        dataAccessGateway.delete(requestModel);
        //File fileName = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes/" + requestModel.getTitle() + ".txt");
        //RemoveStickyNoteResponseModel responseModel = new RemoveStickyNoteResponseModel(fileName.exists());
        return removeStickyNoteremoveStickyNoteOutputBoundary.prepareSuccessView(requestModel);
    }
}
