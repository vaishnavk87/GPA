package entities.calendarSystem;

public class StickyNoteCalendarFactory implements CalendarFactory {
    @Override
    public CalendarInterface create() {
        return new StickyNoteCalendar();
    }
}
