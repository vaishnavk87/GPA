package calendarScreens;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.time.*;

public class DayCell extends JPanel {
    LocalDate date;
    public DayCell(LocalDate date) throws FileNotFoundException, IOException {
        this.date = date;

        JLabel dayNum = new JLabel(String.valueOf(date.getDayOfMonth()));

        this.add(dayNum);
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setOpaque(false);
        //this.add(scrollPane);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), new EmptyBorder(25, 25, 25, 25)));

        File stickyNotesDirectory = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes");
        if (!stickyNotesDirectory.exists()) {
            stickyNotesDirectory.mkdirs();
        }
        for (File fileEntry : stickyNotesDirectory.listFiles()) {
            File temp = new File(stickyNotesDirectory + "/" + fileEntry.toString());
            String[] dateArray;
            String[] formattedData = new String[7];
            BufferedReader getFormat = new BufferedReader(new FileReader(temp));
            for (int i = 0; i < 7; i++) {
                getFormat.readLine();
                formattedData[i] = String.valueOf(getFormat);
            }
            getFormat.close();

            BufferedReader in = new BufferedReader(new FileReader(temp));
            for (int i = 0; i < 5; i++) {
                in.readLine();
            }
            dateArray = String.valueOf(in).split(";");
            for (String s : dateArray) {
                if (this.date.isEqual(LocalDate.parse(s))) {
                    StickyNoteViewPanel tempStickyNote = new StickyNoteViewPanel(formattedData);
                    this.add(tempStickyNote);
                }
            }
            in.close();
        }
    }

    void setDate(LocalDate date) {
        this.date = date;
    }

    LocalDate getDate() {
        return date;
    }
}
