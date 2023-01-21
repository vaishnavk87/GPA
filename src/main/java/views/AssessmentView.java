package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setMark.SetMarkWindowRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import ports.usecases.course.viewCourse.ViewCourseRequest;
import usecases.assessment.SetMark.SetMarkWindowController;
import usecases.course.ViewCourse.ViewCourseController;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class AssessmentView {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public AssessmentView(EntityGateway entityGateway, EntityFactory entityFactory, ViewAssessmentResponse response, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Assessment Group");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label constructor
        JLabel label1 = new JLabel(response.assessmentTitle);
        label1.setBounds((int) (0.066 * WIDTH), (int) (0.133 * HEIGHT) - 25, 370, 20);
        panel.add(label1);

        // Assessment Instances Table constructor
        String[] assessmentInstanceTitles = response.assessmentInstanceTitles;
        Double[] assessmentInstanceWeights = response.assessmentInstanceWeights;
        Double[] assessmentInstanceMarks = response.assessmentInstanceMarks;

        String[][] dataColumns = new String[3][assessmentInstanceTitles.length];
        dataColumns[0] = assessmentInstanceTitles;
        dataColumns[1] = doubleListToStringList(assessmentInstanceWeights);
        dataColumns[2] = doubleListToStringList(assessmentInstanceMarks);

        String[][] data = transpose(dataColumns);
        String[] column = {"Title", "Weight", "Marks"};

        JTable assessmentInstancesTable = new JTable(data, column);
        assessmentInstancesTable.setBounds((int) (0.066 * WIDTH), (int) (0.133 * HEIGHT), (int) (WIDTH - (0.133 * WIDTH)), (int) (HEIGHT * 0.533));
        assessmentInstancesTable.getColumnModel().getColumn(0).setHeaderValue("Instance Title");
        assessmentInstancesTable.getColumnModel().getColumn(1).setHeaderValue("Weight");
        assessmentInstancesTable.getColumnModel().getColumn(2).setHeaderValue("Marks (Click to set, commit, or uncommit)");
        panel.add(assessmentInstancesTable);
        panel.add(assessmentInstancesTable.getTableHeader(), BorderLayout.NORTH);

       Integer[] finalAssessmentInstanceIds = new Integer[assessmentInstanceTitles.length];
       IntStream.range(0, assessmentInstanceTitles.length).forEach(i -> finalAssessmentInstanceIds[i] = i);

        assessmentInstancesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = assessmentInstancesTable.rowAtPoint(evt.getPoint());
                int col = assessmentInstancesTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 2) {
                    Integer assessmentId = finalAssessmentInstanceIds[row];
                    SetMarkWindowRequest request = new SetMarkWindowRequest(response.username, response.courseCode, response.assessmentTitle,
                            response.assessmentInstanceTitles[row], assessmentId);
                    new SetMarkWindowController(request, frame, entityGateway, entityFactory, parentFrame);
                    frame.setVisible(false);
                }
            }
        });

        // Edit Weightscheme Button
        JButton editWeightschemeButton = new JButton("Edit Weightscheme");
        editWeightschemeButton.setBounds(assessmentInstancesTable.getX(), assessmentInstancesTable.getY() + 320 , 160, 50);
        panel.add(editWeightschemeButton);

        editWeightschemeButton.addActionListener(e -> new SetSimpleWeightSchemeView(entityGateway, entityFactory, response.username, response.courseCode, response.assessmentTitle, frame));


        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(assessmentInstancesTable.getX() + 360, assessmentInstancesTable.getY() + 320, 160, 50);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
            parentFrame.dispose(); // parentFrame.setVisible(true);
            ViewCourseRequest request = new ViewCourseRequest(response.username, response.courseCode, response.semesterTitle);
            new ViewCourseController(request, frame, entityGateway, entityFactory, parentFrame);
        });

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
            if (d == null) {
                stringList[index] = "null";
            } else {
                stringList[index] = d.toString();
            }
            index += 1;
        }
        return stringList;
    }

}
