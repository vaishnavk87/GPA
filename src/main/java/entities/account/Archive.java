package entities.account;

import entities.course.Course;

import java.time.LocalDate;
import java.util.List;

public abstract class Archive {
    public abstract List<Course> getCourses();

    public abstract ArchivedCourseData getArchivedCourseData(Course course);

    /**
     * add a course to this archive. This operation will be recorded in terms of the date given by the system clock.
     *
     * @param course   the course to archive
     * @param semester the semester the course was a part of
     */
    public abstract void addCourse(Course course, String semester);

    /**
     * remove a course and its archive data from this archive.
     * @param course the course to remove
     */
    public abstract void removeCourse(Course course);

    public abstract Course getCourseByCode(String courseCode);

    public abstract class ArchivedCourseData {
        public abstract String getSemester();
        public abstract LocalDate getDateArchived();
        public abstract void setSemester(String semester);
        public abstract void setDateArchived(LocalDate dateArchived);
    }

    public interface ArchiveFactory {
        Archive createArchive();
    }
}

