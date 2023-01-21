package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.course.viewCourse.ViewCourseRequest;
import usecases.course.ViewCourse.ViewCourseController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SemesterView {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public SemesterView(EntityGateway entityGateway, EntityFactory entityFactory, ViewSemesterResponse response, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Current Courses");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label constructor
        JLabel label1 = new JLabel(response.semesterTitle, SwingConstants.CENTER);
        label1.setBounds(100, 10, 370, 20);
        panel.add(label1);

        // Courses Table constructor
        String[] courseCodes = response.courseCodes;
        String[] courseTitles = response.courseTitles;
        Double[] courseGrades = response.courseGrades;

        boolean isEmpty = courseCodes.length == 0;
        if (isEmpty) {
            courseCodes = new String[]{"NCA"};
            courseTitles = new String[]{"No Course Added"};
            courseGrades = new Double[]{0.0};
        }

        String[][] dataColumns = new String[3][courseCodes.length];
        dataColumns[0] = courseCodes;
        dataColumns[1] = courseTitles;
        dataColumns[2] = doubleListToStringList(courseGrades);

        String[][] data = transpose(dataColumns);
        String[] column = {"Course Codes", "Course Titles", "Course Grades"};

        JTable coursesTable = new JTable(data, column);
        coursesTable.setBounds((int) (0.066 * WIDTH), (int) (0.133 * HEIGHT), (int) (WIDTH - (0.133 * WIDTH)), (int) (HEIGHT * 0.533));
        panel.add(coursesTable);

        // Running GPA Label
        JLabel label2 = new JLabel("Running GPA: " + response.runningGPA);
        label2.setBounds(coursesTable.getX(), coursesTable.getY() + coursesTable.getHeight() - 25, 210, 100);
        panel.add(label2, BorderLayout.CENTER);

        // Hypo GPA Label
        JLabel label3 = new JLabel("Hypothetical GPA: " + response.hypotheticalGPA);
        label3.setBounds(label2.getX() + 210, label2.getY(), 210, 100);
        panel.add(label3);

        // add course button
        JButton addCourseButton = new JButton("+");
        addCourseButton.setForeground(Color.GREEN);
        addCourseButton.setBounds(label3.getX() + 210, label3.getY() + 25, 50, 50);
        panel.add(addCourseButton);

        addCourseButton.addActionListener(e -> new AddCourseView(entityGateway, entityFactory, response.username, frame));

        // remove course button
        JButton removeCourseButton = new JButton("-");
        removeCourseButton.setForeground(Color.RED);
        removeCourseButton.setBounds(addCourseButton.getX() + 50, addCourseButton.getY(), 50, 50);
        panel.add(removeCourseButton);

        String[] finalCourseCodes = courseCodes;
        if (!isEmpty) {
            coursesTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = coursesTable.rowAtPoint(evt.getPoint());
                    int col = coursesTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        String courseCode = finalCourseCodes[row];
                        ViewCourseRequest request = new ViewCourseRequest(response.username, courseCode, response.semesterTitle);
                        new ViewCourseController(request, frame, entityGateway, entityFactory, frame);
                        frame.setVisible(false);
                    }
                }
            });
        }

        // checklist button
        JButton checklistButton = new JButton("Checklist");
        checklistButton.setBounds(label2.getX(), label2.getY() + 100, 160, 50);
        panel.add(checklistButton);
        checklistButton.addActionListener(e -> new ChecklistView(entityGateway, entityFactory, response.username));

        // calendar button
        JButton calendarButton = new JButton("Calendar");
        calendarButton.setBounds(checklistButton.getX() + 180, checklistButton.getY(), 160, 50);
        panel.add(calendarButton);
        calendarButton.addActionListener(e -> {
            try {
                new CalendarView();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        if (!isEmpty) {
            // showTrend button
            JButton showTrendButton = new JButton("Show Trend");
            showTrendButton.setBounds(calendarButton.getX() + 180, calendarButton.getY(), 160, 50);
            panel.add(showTrendButton);
            showTrendButton.addActionListener(e -> new TrendView(entityGateway, entityFactory, response.trendModel, "Overall"));
        }
        else{
            JButton showTrendButton = new JButton("No Trend");
            showTrendButton.setBounds(calendarButton.getX() + 180, calendarButton.getY(), 160, 50);
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
            stringList[index] = d.toString();
            index += 1;
        }
        return stringList;
    }

}


