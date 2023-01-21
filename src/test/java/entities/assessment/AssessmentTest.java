package entities.assessment;

import entities.MockEntityFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssessmentTest {
    private final MockEntityFactory mockEntityFactory = new MockEntityFactory();

    Assessment testAssessment = mockEntityFactory.createAssessment(
            "Quizzes",
            mockEntityFactory.createSimpleWeight(mockEntityFactory.createWeight(4, 0.2))
            );


    private void setTestMarks(Assessment assessment) {
        assessment.getInstances().get(0).setMark(50.0);
        assessment.getInstances().get(0).setSubmitted(true);
        assessment.getInstances().get(0).setCommitted(true);
        assessment.getInstances().get(1).setMark(100.0);
        assessment.getInstances().get(1).setSubmitted(true);
        assessment.getInstances().get(1).setCommitted(true);
        assessment.getInstances().get(2).setMark(75.0);
    }

    @Test
    public void getTotalWeightTest() {
        Assertions.assertEquals(0.8, testAssessment.getTotalWeight());
    }

    @Test
    public void getTotalNumberOfInstancesTest() {
        Assertions.assertEquals(4, testAssessment.getTotalNumberOfInstances());
    }

    @Test
    public void getCurrentNumberOfInstancesTest() {
        Assertions.assertEquals(4, testAssessment.getCurrentNumberOfInstances());
    }

    @Test
    public void getNumberOfCommittedInstancesTest() {
        Assertions.assertEquals(0, testAssessment.getNumberOfCommittedInstances());

        testAssessment.getInstances().get(0).setMark(90.0);
        Assertions.assertEquals(0, testAssessment.getNumberOfCommittedInstances());

        testAssessment.getInstances().get(0).setSubmitted(true);
        testAssessment.getInstances().get(0).setCommitted(true);
        Assertions.assertEquals(1, testAssessment.getNumberOfCommittedInstances());
    }

    @Test
    public void getNumberOfSubmittedInstancesTest() {
        Assertions.assertEquals(0, testAssessment.getNumberOfCommittedInstances());

        testAssessment.getInstances().get(0).setMark(90.0);
        Assertions.assertEquals(0, testAssessment.getNumberOfCommittedInstances());

        testAssessment.getInstances().get(0).setSubmitted(true);
        Assertions.assertEquals(1, testAssessment.getNumberOfSubmittedInstances());
    }

    @Test
    public void getCommittedMarksTest() {
        setTestMarks(testAssessment);
        double[] expected = {50.0, 100.0};
        Assertions.assertArrayEquals(expected, testAssessment.getCommittedMarks());
    }

    @Test
    public void getAllMarksTest() {
        setTestMarks(testAssessment);
        double[] expected = {50.0, 100.0, 75.0};
        Assertions.assertArrayEquals(expected, testAssessment.getAllMarks());
    }

    @Test
    public void getActualCommittedWeight() {
        setTestMarks(testAssessment);
        double expected = 30.0;
        Assertions.assertEquals(expected, testAssessment.getActualCommittedWeight(), 0.001);
    }

    @Test
    public void getActualHypotheticalWeightTest() {
        setTestMarks(testAssessment);
        double expected = 45.0;
        Assertions.assertEquals(expected, testAssessment.getActualHypotheticalWeight(), 0.001);
    }

    @Test
    public void getMaxPossibleCommittedWeightTest() {
        setTestMarks(testAssessment);
        double expected = 0.4;
        Assertions.assertEquals(expected, testAssessment.getMaxPossibleCommittedWeight(), 0.001);
    }

    @Test
    public void getMaxPossibleSubmittedWeightTest() {
        setTestMarks(testAssessment);
        double expected = 0.4;
        Assertions.assertEquals(expected, testAssessment.getMaxPossibleCommittedWeight(), 0.001);
    }

    @Test
    public void getMaxPossibleHypotheticalWeightTest() {
        setTestMarks(testAssessment);
        double expected = 0.6;
        Assertions.assertEquals(expected, testAssessment.getMaxPossibleHypotheticalWeight(), 0.001);
    }
}
