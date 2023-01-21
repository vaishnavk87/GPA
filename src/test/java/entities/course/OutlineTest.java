package entities.course;

import entities.MockEntityFactory;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;

import java.util.ArrayList;
import java.util.List;

public class OutlineTest {
    private MockEntityFactory mockEntityFactory = new MockEntityFactory();

    private List<AssessmentInstance> createMockInstances(int count, Double mark, boolean commit, boolean submit) {
        List<AssessmentInstance> instances = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            instances.add(mockEntityFactory.createAssessmentInstance(String.format("Instance %d", i), null,
                    mark, commit, submit));
        }
        return instances;
    }

    @Test
    public void getNumberOfAssessmentInstancesCompletedTest() {
        Assessment assessment1 = mockEntityFactory.createAssessment(
                "A1",
                mockEntityFactory.createSimpleWeight(
                        mockEntityFactory.createWeight(3, 0.2)
                ),
                createMockInstances(3, 1.0, true, true)
        );

        Assessment assessment2 = mockEntityFactory.createAssessment(
                "A2",
                mockEntityFactory.createSimpleWeight(
                        mockEntityFactory.createWeight(4, 0.1)
                )
        );
        assessment2.addInstance(mockEntityFactory.createAssessmentInstance(
                "I1", null, 1.0, false, false));
        assessment2.addInstance(mockEntityFactory.createAssessmentInstance(
                "I2", null, 1.0, false, true
        ));

        Outline outlineMock = mockEntityFactory.createOutline();
        outlineMock.addAssessment(assessment1);
        outlineMock.addAssessment(assessment2);

        Assertions.assertEquals(4, outlineMock.getNumberOfAssessmentInstancesCompleted());
    }

    //TODO: implement testing for [getPercentageCompleted, computeRunningGrade, computeHypotheticalWeight]

}
