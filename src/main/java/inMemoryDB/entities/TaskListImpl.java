package inMemoryDB.entities;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.course.Course;
import entities.course.Outline;
import entities.task.Task;
import entities.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListImpl extends TaskList {
    private List<Task> taskList = new ArrayList<>();


    public TaskListImpl(Account account){
        List<Course> courseList = account.getSemester().getRunningCourses();
        TaskImpl task;
        List<List<AssessmentInstance>> assessmentInstances = new ArrayList<>();
        for (Course i : courseList) {
            Outline outline = i.getOutline();
            List<Assessment> assessmentList = outline.getAssessments();
            List<AssessmentInstance> assessmentInstancesList = new ArrayList<>();
            for (Assessment k : assessmentList) {
                assessmentInstancesList.addAll(k.getInstances());
            }
            assessmentInstances.add(assessmentInstancesList);
        }
        for (int j = 0; j < courseList.size(); j++) {
            for (AssessmentInstance k : assessmentInstances.get(j)) {
                task = new TaskImpl(courseList.get(j).getCourseCode(), k);
                taskList.add(task);
            }
        }
    }

    @Override
    public List<Task> getTaskList() {
        return taskList;
    }
}
