package entities.gpa;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Construct GPACalculations that consist of different methods for various functionality ranging from computation
 * of percentage to GPA, converting list marks to list of GPA marks, computing the average of marks,
 * computation of percentage to lettered Grade, converting list marks to list of lettered Grades, and overall GPA
 * consisting of GPA and weight. GPA scale and lettered Grade are representative of UofT grading scheme.
 */

public class GPACalculation {

    /**
     * Takes a double of mark in terms of percentage and converts to double in terms of GPA point scale
     *
     * @param percentage is a double that represents the mark in a course
     * @return a double of GPA value
     */

    public static double percentToGPA(double percentage) {
        if (percentage >= 85.0) {
            return 4.0;
        } else if (percentage >= 80.0) {
            return 3.7;
        } else if (percentage >= 77.0) {
            return 3.3;
        } else if (percentage >= 73.0) {
            return 3.0;
        } else if (percentage >= 70.0) {
            return 2.7;
        } else if (percentage >= 67.0) {
            return 2.3;
        } else if (percentage >= 63.0) {
            return 2.0;
        } else if (percentage >= 60.0) {
            return 1.7;
        } else if (percentage >= 57.0) {
            return 1.3;
        } else if (percentage >= 53.0) {
            return 1.0;
        } else if (percentage >= 50.0) {
            return 0.7;
        } else {
            return 0.0;
        }
    }

    /**
     * Takes list of double consisting of course marks (in terms percentage) and converting values to list of double
     * of GPA
     *
     * @param marksPercentage list of double consisting of course marks in percentage
     * @return gpalist which is a list of double of marks in terms of GPA point scale UofT
     */

    public static List<Double> computeGPAList(List<Double> marksPercentage) {
        List<Double> gpalist = new ArrayList<>();
        for (int i = 0; i < marksPercentage.size(); i++) {
            gpalist.add(percentToGPA(marksPercentage.get(i)));
        }
        //System.out.println(gpalist);
        return gpalist;
    }

    /**
     * Takes list of double consisting of course marks in terms of percentage and take the average of these marks
     *
     * @param marksPercentage1 a list of double consisting of course marks in terms of percentage
     * @return a double of the computed average
     */
    public static double computeAverage(List<Double> marksPercentage1) {
        double average;
        double sum = 0;
        for (int i = 0; i < marksPercentage1.size(); i++) {
            sum += marksPercentage1.get(i);
        }
        average = Math.round((sum / marksPercentage1.size()) * 100.0) / 100.0;
        //System.out.println(average);
        return average;
    }

    /**
     * Takes a double in terms of percentage and converts it to lettered Grade based on UofT grading scheme
     *
     * @param percentage1 a double representing course mark in terms of percentage
     * @return a String that is representative of mark in terms of lettered Grade
     */

    public static String percentToGrade(double percentage1) {
        if (percentage1 >= 90) {
            return "A+";
        } else if (percentage1 >= 85) {
            return "A";
        } else if (percentage1 >= 80) {
            return "A-";
        } else if (percentage1 >= 77) {
            return "B+";
        } else if (percentage1 >= 73) {
            return "B";
        } else if (percentage1 >= 70) {
            return "B-";
        } else if (percentage1 >= 67) {
            return "C+";
        } else if (percentage1 >= 63) {
            return "C";
        } else if (percentage1 >= 60) {
            return "C-";
        } else if (percentage1 >= 57) {
            return "D+";
        } else if (percentage1 >= 55) {
            return "D";
        } else if (percentage1 >= 50) {
            return "D-";
        } else {
            return "F";
        }
    }

    /**
     * Takes list of double consisting of course marks in terms of percentage and converts to list of lettered Grades
     *
     * @param marksPercentage2 a list of double
     * @return gradelist which is a list of string consisting of lettered Grade
     */
    public static List<String> computeGrade(List<Double> marksPercentage2) {
        List<String> gradelist = new ArrayList<>();
        for (int i = 0; i < marksPercentage2.size(); i++) {
            gradelist.add(percentToGrade(marksPercentage2.get(i)));
        }
        //System.out.println(gradelist);
        return gradelist;
    }

    /**
     * Takes a list of double of markpercentage (course marks) and a list of courseCredit (0.5 or 1 depending on
     * half-year or full-year). Computes overall GPA accounting for course credit.
     *
     * @param markpercentage is a list of double consisting of course marks
     * @param courseCredits  is a list of double consisting of course credits
     * @return a double of the overall GPA to 2 decimal places
     */
    public static double overallGPA(Double[] markpercentage, Double[] courseCredits) {
        double sumGPA = 0;
        double sumCredits = 0;
        double totalGpa;
        List<Double> gpalist = new ArrayList<>();
        List<Double> overallGrade = new ArrayList<>();

        for (int i = 0; i < courseCredits.length; i++) {
            sumCredits += courseCredits[i];
        }
        for (int j = 0; j < markpercentage.length; j++) {
            gpalist.add(percentToGPA(markpercentage[j]));
        }
        for (int s = 0; s < gpalist.size(); s++) {
            overallGrade.add(gpalist.get(s) * courseCredits[s]);
        }
        for (int v = 0; v < overallGrade.size(); v++) {
            sumGPA += overallGrade.get(v);
        }
        totalGpa = Math.round((sumGPA / sumCredits) * 100.0) / 100.0;
        //System.out.println(totalGpa);
        return totalGpa;
    }

//    public static void main(String[] args) {
//        System.out.println("GPA: ");
//        computeGPAList(List.of(90.0, 85.0, 73.0, 84.9));
//
//        System.out.println("Average: ");
//        computeAverage(List.of(90.0, 85.0, 73.0, 84.9));
//
//        System.out.println("Letters: ");
//        computeGrade(List.of(90.0, 85.0, 73.0, 84.9));
//
//        System.out.println("Whole: ");
//        overallGPA(List.of(90.0, 75.0, 73.0, 81.0), List.of(1.0, 0.5, 0.5, 1.0));
//
//
//    }

}
