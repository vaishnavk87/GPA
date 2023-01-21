package ports.usecases.calendar.writeToStickyNoteUseCase;

import entities.calendarSystem.StickyNoteInterface;

public class WriteToStickyNoteResponseModel {
//setters, and getters
    private StickyNoteInterface stickyNote;

    public WriteToStickyNoteResponseModel(StickyNoteInterface stickyNote) {
        this.stickyNote = stickyNote;
    }

    void setStickyNote(StickyNoteInterface stickyNote) {
        this.stickyNote = stickyNote;
    }
    public StickyNoteInterface getStickyNote() {
        return stickyNote;
    }

}
