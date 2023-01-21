package views;

import calendarScreens.CalendarPanel;

import javax.swing.*;
import java.io.IOException;

/**
 * Tests the calendar portion of the GUI. (Add CalendarPanel to Main GUI for final version.)
 * */
public class CalendarView/* implements ActionListener*/ {
    CalendarPanel calPanel;
    JFrame frame;

    public CalendarView() throws IOException {
        frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calPanel = new CalendarPanel();
        frame.setContentPane(calPanel);
        frame.pack();
        frame.setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        e.getActionCommand();
//    }

    private static void runCalendarMain() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        CalendarView cal = new CalendarView();
    }

    public static void main(String[] args) {
        //Runnable interface allows for threading
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        runCalendarMain();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
