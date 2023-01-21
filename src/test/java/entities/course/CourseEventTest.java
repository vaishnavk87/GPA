package entities.course;

import entities.MockEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseEventTest {
    MockEntityFactory mockEntityFactory = new MockEntityFactory();

    @Test
    public void toStringTest() {
        CourseEvent courseEvent = mockEntityFactory.createCourseEvent("Lecture", DayOfWeek.MONDAY,
                LocalTime.of(10, 10), LocalTime.of(11, 0), "BA 3116");
        Assertions.assertEquals("Lecture - MONDAY from 10:10 to 11:00 - BA 3116", courseEvent.toString());
    }
}
