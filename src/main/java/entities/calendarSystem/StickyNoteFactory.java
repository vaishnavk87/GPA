package entities.calendarSystem;

import java.time.*;

public interface StickyNoteFactory {
    StickyNoteInterface create(String title, String type, String location, String time, String dotJots, LocalDate[] dates, DayOfWeek[] daysOfWeekToPostOn);
}
