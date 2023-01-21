package entities.gpa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GPACalculationTest {

    @Test
    public void percentToGPASingle(){
        assertEquals (4.0, GPACalculation.percentToGPA(86.0));
    }
    @Test
    public void percentToGPASingle2(){
        assertEquals(3.0, GPACalculation.percentToGPA(75));
    }
    @Test
    public void percentListToGPAList(){
        assertEquals(List.of(4.0, 4.0, 3.0, 3.7),GPACalculation.computeGPAList(List.of(90.0, 85.0, 73.0, 84.9)));
    }
    @Test
    public void computeAverage(){
        assertEquals(83.23, GPACalculation.computeAverage(List.of(90.0, 85.0, 73.0, 84.9)));
    }
    @Test
    public void percentToLetterGrade(){
        assertEquals("A+", GPACalculation.percentToGrade(95.0));
    }
    @Test
    public void percentListToGradeList(){
        assertEquals(List.of("A+", "A", "B", "A-"), GPACalculation.computeGrade(List.of(90.0, 85.0, 73.0, 84.9)));
    }
    @Test
    public void computeOverallGPA(){
        Double[] grades = {90.0, 75.0, 73.0, 81.0};
        Double[] credits = {1.0, 0.5, 0.5, 1.0};
        assertEquals(3.57, GPACalculation.overallGPA(grades, credits));
    }


}