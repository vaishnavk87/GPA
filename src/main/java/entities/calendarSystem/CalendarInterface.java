package entities.calendarSystem;

import java.time.*;
import java.util.*;

public interface CalendarInterface {
    void updateDateAndTime();

    LocalDateTime getCurrDateAndTime();

    LocalDate getToday();

    LocalDate getSeekedDate();

    //ArrayList<StickyNoteInterface> getStickyNotes();

//    void toggleAssessments(boolean toggle);
//
//    void toggleTimetable(boolean toggle);
//
//    void toggleMiscellaneous(boolean toggle);
}
