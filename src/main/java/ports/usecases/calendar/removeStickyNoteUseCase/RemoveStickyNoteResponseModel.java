package ports.usecases.calendar.removeStickyNoteUseCase;

public class RemoveStickyNoteResponseModel {
    boolean fileDeletedOrNot;

    public RemoveStickyNoteResponseModel(boolean fileDeletedOrNot) {
        this.fileDeletedOrNot = fileDeletedOrNot;
    }

    void setFileDeletedOrNot(boolean fileDeletedOrNot) {
        this.fileDeletedOrNot = fileDeletedOrNot;
    }

    boolean getFileDeletedOrNot() {
        return fileDeletedOrNot;
    }
}
