package entities;

import entities.assessment.Assessment;
import entities.assessment.Assessment.AssessmentFactory;
import entities.assessment.AssessmentInstance;
import entities.assessment.AssessmentInstance.AssessmentInstanceFactory;
import entities.course.Course;
import entities.course.Course.CourseFactory;
import entities.course.CourseEvent;
import entities.course.Outline;
import entities.course.Outline.OutlineFactory;
import entities.weightScheme.OrderedWeight;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;
import entities.weightScheme.WeightScheme;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MockEntityFactory implements
        CourseFactory,
        CourseEvent.CourseEventFactory,
        OutlineFactory,
        AssessmentFactory,
        AssessmentInstanceFactory,
        Weight.WeightFactory,
        SimpleWeight.SimpleWeightFactory,
        OrderedWeight.OrderedWeightFactory {
    private class CourseMock extends Course {
        private String courseCode;
        private String courseName;
        private float credit;
        private Outline outline;
        private ArrayList<CourseEvent> courseEvents = new ArrayList<>();

        @Override
        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        @Override
        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        @Override
        public float getCredit() {
            return credit;
        }

        @Override
        public void setCredit(float credit) {
            this.credit = credit;
        }

        @Override
        public Outline getOutline() {
            return outline;
        }

        @Override
        public void setOutline(Outline outline) {
            this.outline = outline;
        }

        @Override
        public ArrayList<CourseEvent> getCourseEvents() {
            return courseEvents;
        }

        @Override
        public CourseEvent getCourseEventByTitle(String title) {
            for (CourseEvent courseEvent : courseEvents) {
                if (courseEvent.getTitle().equals(title)) {
                    return courseEvent;
                }
            }
            return null;
        }

        @Override
        public void addCourseEvent(CourseEvent courseEvent) {
            if (!courseEvents.contains(courseEvent)) {
                courseEvents.add(courseEvent);
            }
        }

        @Override
        public void removeCourseEvent(CourseEvent courseEvent) {
            courseEvents.remove(courseEvent);
        }
    }

    private class CourseEventMock extends CourseEvent {
        private String title;
        private DayOfWeek day;
        private LocalTime startTime;
        private LocalTime endTime;
        private String location;
        public CourseEventMock(String title, DayOfWeek day, LocalTime startTime, LocalTime endTime, String location) {
            this.title = title;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.location = location;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public DayOfWeek getDay() {
            return day;
        }

        public void setDay(DayOfWeek day) {
            this.day = day;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    private class OutlineMock extends Outline {
        private ArrayList<Assessment> assessments = new ArrayList<>();

        @Override
        public ArrayList<Assessment> getAssessments() {
            return assessments;
        }

        @Override
        public ArrayList<String> getAssessmentsTitles() {
            ArrayList<String> titles = new ArrayList<>();
            for (Assessment assessment : assessments) {
                titles.add(assessment.getTitle());
            }
            return titles;
        }

        @Override
        public ArrayList<String> getAssessmentsSingularTitles() {
            ArrayList<String> singularTitles = new ArrayList<>();
            for (Assessment assessment : assessments) {
                singularTitles.add(assessment.getSingularTitle());
            }
            return singularTitles;
        }

        @Override
        public ArrayList<Integer> getAssessmentsNumberOfInstances() {
            ArrayList<Integer> numberOfInstances = new ArrayList<>();
            for (Assessment assessment : assessments) {
                numberOfInstances.add(assessment.getTotalNumberOfInstances());
            }
            return numberOfInstances;
        }

        @Override
        public ArrayList<Double> getAssessmentsWeights() {
            ArrayList<Double> weights = new ArrayList<>();
            for (Assessment assessment : assessments) {
                weights.add(assessment.getTotalWeight());
            }
            return weights;
        }

        @Override
        public Assessment getAssessmentByTitle(String assessmentTitle){
            for (Assessment assessment : assessments) {
                if (assessment.getTitle().equals(assessmentTitle)) {
                    return assessment;
                }
            }
            return null;
        }

        @Override
        public void addAssessment(Assessment assessment) {
            if (!assessments.contains(assessment)) {
                assessments.add(assessment);
            }
        }

        @Override
        public void removeAssessment(Assessment assessment) {
            assessments.remove(assessment);
        }
    }

    private class AssessmentMock extends Assessment {
        private String title;
        private WeightScheme weightScheme;
        private ArrayList<AssessmentInstance> instances = new ArrayList<>();

        private String singularTitle;

        public AssessmentMock(String title, WeightScheme weightScheme) {
            this.title = title;
            this.weightScheme = weightScheme;
            this.singularTitle = toSingular(this.title);

            for (int i = 0, j = 1; i < this.getTotalNumberOfInstances(); i++, j++) {
                this.instances.add(new AssessmentInstanceMock(this.singularTitle + " #" + j));
            }
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getSingularTitle() {
            return singularTitle;
        }

        @Override
        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public WeightScheme getWeightScheme() {
            return weightScheme;
        }

        // TODO: ensure setting weightscheme adjusts instance list
        @Override
        public void setWeightScheme(WeightScheme weightScheme) {
            this.weightScheme = weightScheme;
        }

        @Override
        public ArrayList<AssessmentInstance> getInstances() {
            return instances;
        }

        @Override
        public void addInstance(AssessmentInstance instance) {
            if (getCurrentNumberOfInstances() < getTotalNumberOfInstances()) {
                // TODO: throw appropriate error
            }
            if (!instances.contains(instance)) {
                instances.add(instance);
            }
        }

        @Override
        public void removeInstance(AssessmentInstance instance) {
            instances.remove(instance);
        }

        @Override
        public String toSingular(String title) {
            if (title == "Quizzes"){
                return "Quiz";
            }
            if (title.charAt(title.length()-1) == 's'){
                return singularTitle.substring(0, title.length()-1);
            }
            return title;
        }
    }

    public class AssessmentInstanceMock extends AssessmentInstance implements Serializable {
        private String title;
        private LocalDateTime deadline;
        private boolean isCommitted;
        private boolean isSubmitted;
        private Double mark;

        public AssessmentInstanceMock(String title) {
            this.title = title;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public LocalDateTime getDeadline() {
            return deadline;
        }

        @Override
        public void setDeadline(LocalDateTime deadline) {
            this.deadline = deadline;
        }

        @Override
        public boolean isCommitted() {
            return isCommitted;
        }

        @Override
        public void setCommitted(boolean committed) {
            isCommitted = committed;
        }

        @Override
        public boolean isSubmitted() {
            return isSubmitted;
        }

        @Override
        public void setSubmitted(boolean submitted) {
            isSubmitted = submitted;
        }

        @Override
        public Double getMark() {
            return mark;
        }

        @Override
        public void setMark(Double mark) {
            this.mark = mark;
        }
    }

    public class WeightMock extends Weight {
        private int numberOfInstances;
        private double weightOfEachInstance;

        /**
         * Create a new Weight object
         *
         * @param numberOfInstances    must be positive
         * @param weightOfEachInstance must be between 0 and 1
         */
        public WeightMock(int numberOfInstances, double weightOfEachInstance) {
            this.numberOfInstances = numberOfInstances;
            this.weightOfEachInstance = weightOfEachInstance;
        }

        public int getNumberOfInstances() {
            return numberOfInstances;
        }

        public double getWeightOfEachInstance() {
            return weightOfEachInstance;
        }

        public void setNumberOfInstances(int numberOfInstances) {
            this.numberOfInstances = numberOfInstances;
        }

        public void setWeightOfEachInstance(double weightOfEachInstance) {
            this.weightOfEachInstance = weightOfEachInstance;
        }
    }

    public class SimpleWeightMock extends SimpleWeight {
        private Weight weight;

        public SimpleWeightMock(Weight weight) {
            this.weight = weight;
        }

        @Override
        public Weight getWeight() {
            return weight;
        }

        @Override
        public void setWeight(Weight weight) {
            this.weight = weight;
        }
    }

    public class OrderedWeightMock extends OrderedWeight {
        private Weight[] orderedWeights;

        /**
         * Create new OrderedWeight, with (orderedWeights) being an array of Weight objects
         * @param orderedWeights must be sorted from lowest impact to highest impact.
         */
        public OrderedWeightMock(Weight[] orderedWeights) {
            this.orderedWeights = orderedWeights;
        }

        public Weight[] getOrderedWeights() {
            return orderedWeights;
        }

        public void setOrderedWeights(Weight[] orderedWeights) {
            this.orderedWeights = orderedWeights;
        }
    }



    @Override
    public Course createCourse() {
        return new CourseMock();
    }

    @Override
    public CourseEvent createCourseEvent(String type, DayOfWeek day, LocalTime startTime, LocalTime endTime,
                                         String location) {
        return new CourseEventMock(type, day, startTime, endTime, location);
    }

    @Override
    public Outline createOutline() {
        return new OutlineMock();
    }

    @Override
    public Assessment createAssessment(String title, WeightScheme weightScheme) {
        return new AssessmentMock(title, weightScheme);
    }

    @Override
    public Assessment createAssessment(String title, WeightScheme weightScheme, List<AssessmentInstance> instances) {
        if (instances.size() > weightScheme.getNumberOfInstances()) {
            throw new IllegalArgumentException("instances size exceeds weightScheme's number of instances");
        }
        Assessment assessment = createAssessment(title, weightScheme);
        for (AssessmentInstance instance : instances) {
            assessment.addInstance(instance);
        }
        return assessment;
    }

    @Override
    public AssessmentInstance createAssessmentInstance(String title, LocalDateTime deadline, Double mark,
                                                       boolean isCommitted, boolean isSubmitted)
            throws IllegalArgumentException {
        if (mark != null && (mark < 0 || mark > 100)) {
            throw new IllegalArgumentException(String.format("Mark %f is not valid", mark));
        }
        if (isCommitted && !isSubmitted) {
            // TODO: is this a valid exception? Would it be better to override isSubmitted as true?
            throw new IllegalArgumentException("AssessmentIntance cannot be committed and not submitted");
        }

        AssessmentInstance instance = new AssessmentInstanceMock(title);
        instance.setSubmitted(isSubmitted);
        instance.setCommitted(isCommitted);
        instance.setMark(mark);

        return instance;
    }

    @Override
    public Weight createWeight(int numberOfInstances, double weightOfEachInstance) {
        return new WeightMock(numberOfInstances, weightOfEachInstance);
    }

    @Override
    public SimpleWeight createSimpleWeight(Weight weight) {
        return new SimpleWeightMock(weight);
    }

    @Override
    public OrderedWeight createOrderedWeight(Weight[] orderedWeights) {
        return new OrderedWeightMock(orderedWeights);
    }
}