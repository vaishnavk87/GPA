package calendarScreens;

import javax.swing.*;
import java.awt.*;

public class StickyNoteViewPanel extends JPanel {
    private String[] formattedData;

    public StickyNoteViewPanel(String[] formattedData) {
        this.formattedData = formattedData;

        JLabel title = new JLabel("Title: " + this.formattedData[0]);
        JLabel type = new JLabel("Type: " + this.formattedData[1]);
        JLabel location = new JLabel("Location: " + this.formattedData[2]);
        JLabel time = new JLabel("Time: " + this.formattedData[3]);
        JLabel dates = new JLabel("Dates: " + this.formattedData[4]);
        JLabel daysOfWeek = new JLabel("Days of week: " + this.formattedData[5]);
        JTextArea dotJots = new JTextArea(this.formattedData[6]);
        dotJots.setBounds(10, 30, 200, 200);
        dotJots.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(title, gbc);
        gbc.gridy = 1;
        this.add(type, gbc);
        gbc.gridy = 2;
        this.add(location, gbc);
        gbc.gridy = 3;
        this.add(time, gbc);
        gbc.gridy = 4;
        this.add(dates, gbc);
        gbc.gridy = 5;
        this.add(daysOfWeek, gbc);
        gbc.gridy = 6;
        this.add(dotJots, gbc);
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setOpaque(false);

        //colours
        if (formattedData[1].equalsIgnoreCase("Timetable")) {
            Color timetableColor = new Color(204, 255, 255);
            this.setBackground(timetableColor);
        }
        else if (formattedData[1].equalsIgnoreCase("Assessments")) {
            Color assessmentColor = new Color(255, 204, 204);
            this.setBackground(assessmentColor);
        }
        else /*if (formattedData[1].equalsIgnoreCase("Miscellaneous"))*/ {
            Color miscellaneousColor = new Color(255, 255, 204);
            this.setBackground(miscellaneousColor);
        }

        this.setSize(5, 5);
    }

    void setFormattedData(String[] formattedData) {
        this.formattedData = formattedData;
    }

    String[] getFormattedData() {
        return formattedData;
    }

}
