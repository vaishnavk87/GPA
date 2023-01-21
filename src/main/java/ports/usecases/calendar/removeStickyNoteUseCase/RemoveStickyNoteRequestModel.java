package ports.usecases.calendar.removeStickyNoteUseCase;

public class RemoveStickyNoteRequestModel {
    String title;

    public RemoveStickyNoteRequestModel(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
