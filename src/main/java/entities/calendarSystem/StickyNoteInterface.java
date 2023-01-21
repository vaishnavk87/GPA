package entities.calendarSystem;

import java.util.*;
import java.time.*;

public interface StickyNoteInterface {

    String getTitle();

    String getType();

    String getLocation();

    String getDotJots();

    LocalDate[] getDates();

    String getTime();

    DayOfWeek[] getDaysOfWeekToPostOn();
}
