package entities.calendarSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

class StickyNoteCalendar implements CalendarInterface {
    LocalDateTime currDateAndTime;
    LocalDate today;
    LocalDate seekedDate;
    int x = 0;
    private ArrayList<StickyNoteInterface> stickyNotes = new ArrayList<>();
    private boolean viewAssessment = true;
    private boolean viewTimetable = true;
    private boolean viewMiscellaneous = true;

    StickyNoteCalendar() {
        currDateAndTime = LocalDateTime.now();
        today = LocalDate.now();
        seekedDate = LocalDate.now();
    }
    @Override
    public void updateDateAndTime() {
        currDateAndTime = LocalDateTime.now();
        today = LocalDate.now();
        seekedDate = LocalDate.now();
    }

    @Override
    public LocalDateTime getCurrDateAndTime() {
        return currDateAndTime;
    }

    @Override
    public LocalDate getToday() {
        return today;
    }

    @Override
    public LocalDate getSeekedDate() {
        return seekedDate;
    }

    public ArrayList<StickyNoteInterface> getStickyNotes() {
        return stickyNotes;
    }

//    @Override
//    public void toggleAssessments(boolean toggle) {
//        this.viewAssessment = toggle;
//    }
//
//    @Override
//    public void toggleTimetable(boolean toggle) {
//        this.viewTimetable = toggle;
//    }
//
//    @Override
//    public void toggleMiscellaneous(boolean toggle) {
//        this.viewMiscellaneous = toggle;
//    }

    public boolean getViewAssessment() {
        return viewAssessment;
    }

    public boolean getViewTimetable() {
        return viewTimetable;
    }

    public boolean getViewMiscellaneous() {
        return viewMiscellaneous;
    }
}
