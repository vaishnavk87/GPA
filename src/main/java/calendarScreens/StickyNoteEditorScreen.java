package calendarScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StickyNoteEditorScreen extends JPanel implements ActionListener {

    JLabel titleLabel, stickyNoteLabel, typeLabel, locationLabel, timeLabel, datesLabel, daysOfWeekLabel, dotJotsLabel;
    JTextField title = new JTextField(20);
    JComboBox<String> type = new JComboBox<>(new String[]{"Assessment", "Timetable", "Miscellaneous"});
    JTextField dates = new JTextField(20);
    JTextField daysOfWeek = new JTextField(20);
    JTextField time = new JTextField(20);
    JTextField location = new JTextField(20);
    JTextArea dotJots = new JTextArea();
    JButton saveButton;
    JButton removeStickyNoteButton;
    WriteToStickyNoteController stickyNoteController;
    RemoveStickyNoteController removeStickyNoteController;

    public StickyNoteEditorScreen(WriteToStickyNoteController writeToController, RemoveStickyNoteController removeController) {
        this.stickyNoteController = writeToController;
        this.removeStickyNoteController = removeController;

        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        titleLabel = new JLabel("Title: ");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dotJots.setBounds(10, 30, 200, 200);
        dotJots.setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        stickyNoteLabel = new JLabel("Sticky Note: ");
        this.add(stickyNoteLabel, gbc);
        gbc.gridy = 1;
        this.add(titleLabel, gbc);
        gbc.gridx = 1;
        this.add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        typeLabel = new JLabel("Type: ");
        this.add(typeLabel, gbc);
        gbc.gridx = 1;
        type.setEditable(true);
        this.add(type, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        locationLabel = new JLabel("Location: ");
        this.add(locationLabel, gbc);
        gbc.gridx = 1;
        this.add(location, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        timeLabel = new JLabel("Time: ");
        this.add(timeLabel, gbc);
        gbc.gridx = 1;
        this.add(time, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        datesLabel = new JLabel("Dates: ");
        this.add(datesLabel, gbc);
        gbc.gridx = 1;
        this.add(dates, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        daysOfWeekLabel = new JLabel("Days of week: ");
        this.add(daysOfWeekLabel, gbc);
        gbc.gridx = 1;
        this.add(daysOfWeek, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        dotJotsLabel = new JLabel("Text: ");
        this.add(dotJotsLabel, gbc);
        gbc.gridx = 1;
        this.add(dotJots, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        this.add(saveButton, gbc);
        gbc.gridx = 1;
        removeStickyNoteButton = new JButton("Remove");
        removeStickyNoteButton.addActionListener(this);
        this.add(removeStickyNoteButton, gbc);
        //gbc.gridx = 2;
        //JToggleButton notify = new JToggleButton("notify");
        //this.add(notify, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if (event.getSource() == saveButton) {
            try {
                stickyNoteController.create(title.getText(), (String) type.getSelectedItem(), location.getText(), time.getText(), dotJots.getText(), dates.toString(), daysOfWeek.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        if (event.getSource() == removeStickyNoteButton) {
            removeStickyNoteController.delete(title.getText());
        }
    }
}
