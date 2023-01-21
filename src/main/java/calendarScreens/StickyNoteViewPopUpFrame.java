package calendarScreens;

import javax.swing.*;

public class StickyNoteViewPopUpFrame extends JFrame {
    public StickyNoteViewPopUpFrame(StickyNoteViewPanel stickyNoteViewPanel) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stickyNoteViewPanel.setSize(210, 500);
        this.setContentPane(stickyNoteViewPanel);
        this.pack();
        this.setVisible(true);
    }
}
