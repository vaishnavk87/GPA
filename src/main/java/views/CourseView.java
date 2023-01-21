package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.archiveCourse.ArchiveCourseRequest;
import ports.usecases.account.viewSemester.ViewSemesterRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentRequest;
import ports.usecases.course.viewCourse.ViewCourseResponse;
import usecases.account.ArchiveCourse.ArchiveCourseController;
import usecases.account.ViewSemester.ViewSemesterController;
import usecases.assessment.ViewAssessment.ViewAssessmentController;

import javax.swing.*;
import java.awt.*;

public class CourseView {
    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public CourseView(EntityGateway entityGateway, EntityFactory entityFactory, ViewCourseResponse response, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Course");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label constructor
        JLabel label1 = new JLabel(response.courseCode + ": " + response.courseTitle + " - " + response.credit + " credit(s)", SwingConstants.CENTER);
        label1.setBounds(100, 10, 370, 20);
        panel.add(label1);

        // TODO course event table

        // Assessments Table constructor
        String[] assessmentTitles = response.assessmentTitles;
        Integer[] assessmentNumberOfInstances = response.assessmentNumberOfInstances;
        Double[] assessmentWeights = response.assessmentWeights;

        boolean isEmpty = assessmentTitles.length == 0;
        if (isEmpty) {
            assessmentTitles = new String[]{"No Assessment"};
            assessmentNumberOfInstances = new Integer[]{0};
            assessmentWeights = new Double[]{0.0};
        }

        String[][] dataColumns = new String[3][assessmentTitles.length];
        dataColumns[0] = assessmentTitles;
        dataColumns[1] = integerListToStringList(assessmentNumberOfInstances);
        dataColumns[2] = doubleListToStringList(assessmentWeights);

        String[][] data = transpose(dataColumns);
        String[] column = {"Assessment Titles", "Number of Instances", "Assessment Weight"};

        JTable assessmentsTable = new JTable(data, column);
        assessmentsTable.setBounds((int) (0.066 * WIDTH), (int) (0.133 * HEIGHT), (int) (WIDTH - (0.133 * WIDTH)), (int) (HEIGHT * 0.533));
        panel.add(assessmentsTable);

        // Running Grade Label
        JLabel label2 = new JLabel("Running Grade: " + response.runningGrade + " (" + response.runningLetteredGrade + ")");
        label2.setBounds(assessmentsTable.getX(), assessmentsTable.getY() + assessmentsTable.getHeight() - 25, 210, 100);
        panel.add(label2, BorderLayout.CENTER);

        // Hypo Grade Label
        JLabel label3 = new JLabel("Hypothetical Grade: " + response.hypotheticalGrade + " (" + response.hypotheticalLetteredGrade + ")");
        label3.setBounds(label2.getX() + 210, label2.getY(), 210, 100);
        panel.add(label3);

        // add assessment button
        JButton addSimpleAssessmentButton = new JButton("+");
        addSimpleAssessmentButton.setForeground(Color.GREEN);
        addSimpleAssessmentButton.setBounds(label3.getX() + 210, label3.getY() + 25, 100, 50);
        panel.add(addSimpleAssessmentButton);


        addSimpleAssessmentButton.addActionListener(e -> new AddSimpleAssessmentView(entityGateway, entityFactory, response.username, response.courseCode, frame));

        String[] finalAssessmentTitles = assessmentTitles;
        if (!isEmpty) {
            assessmentsTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = assessmentsTable.rowAtPoint(evt.getPoint());
                    int col = assessmentsTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        String assessmentTitle = finalAssessmentTitles[row];
                        ViewAssessmentRequest request = new ViewAssessmentRequest(response.username, response.courseCode, assessmentTitle, response.semesterTitle);
                        new ViewAssessmentController(request, frame, entityGateway, entityFactory, parentFrame);
                        frame.setVisible(false);
                    }
                }
            });
        }



        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(label2.getX() + 360, label2.getY() + 100, 160, 50);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
            parentFrame.dispose(); // parentFrame.setVisible(true);
            ViewSemesterRequest request = new ViewSemesterRequest(response.username, response.semesterTitle);
            new ViewSemesterController(request, frame, entityGateway, entityFactory, frame);
        });

        // Archive course button
        JButton archiveCourseButton = new JButton("Archive Course");
        archiveCourseButton.setBounds(label2.getX(), label2.getY() + 100, 160, 50);
        panel.add(archiveCourseButton);

        archiveCourseButton.addActionListener(e -> {
            ArchiveCourseRequest request = new ArchiveCourseRequest(response.username, response.courseCode);
            new ArchiveCourseController(request, frame, entityGateway, entityFactory, frame);
        });

        if (!isEmpty) {
            // showTrend button
            JButton showCourseTrendButton = new JButton("Show Course Trend");
            showCourseTrendButton.setBounds(archiveCourseButton.getX() + 180, archiveCourseButton.getY(), 160, 50);
            panel.add(showCourseTrendButton);
            showCourseTrendButton.addActionListener(e -> new TrendView(entityGateway, entityFactory, response.trendModel, response.courseTitle));
        }
        else{
            JButton showTrendButton = new JButton("No Course Trend");
            showTrendButton.setBounds(archiveCourseButton.getX() + 180, archiveCourseButton.getY(), 160, 50);
            panel.add(showTrendButton);
        }

        frame.setVisible(true);

    }



    private static String[][] transpose(String[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        String[][] transposedMatrix = new String[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                transposedMatrix[x][y] = matrix[y][x];
            }
        }

        return transposedMatrix;
    }

    private String[] doubleListToStringList(Double[] doubleList) {
        String[] stringList = new String[doubleList.length];
        int index = 0;
        for (Double d : doubleList) {
            double rounded = Math.round(d * 100.0) / 100.0;
            stringList[index] = Double.toString(rounded);
            index += 1;
        }
        return stringList;
    }

    private String[] integerListToStringList(Integer[] integerList) {
        String[] stringList = new String[integerList.length];
        int index = 0;
        for (Integer i : integerList) {
            stringList[index] = i.toString();
            index += 1;
        }
        return stringList;
    }

}
