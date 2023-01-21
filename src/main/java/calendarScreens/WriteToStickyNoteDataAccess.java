package calendarScreens;

import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteDsGateway;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteRequestModel;

import java.io.*;

public class WriteToStickyNoteDataAccess implements WriteToStickyNoteDsGateway {

    /**
     * Writes the sticky note's text file.
     * */
    @Override
    public void save(WriteToStickyNoteRequestModel requestModel) throws IOException {
        File stickyNotesDirectory = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes");
        if (!stickyNotesDirectory.exists()) {
            stickyNotesDirectory.mkdirs();
        }

        File fileName = new File(System.getProperty("user.dir"), "src/main/java/inMemoryDB/StickyNotes/" + requestModel.getTitle() + ".txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(requestModel.getTitle());
        out.newLine();
        out.write(requestModel.getType());
        out.newLine();
        out.write(requestModel.getLocation());
        out.newLine();
        out.write(requestModel.getTime());
        out.newLine();
        StringBuilder tempDates = new StringBuilder();
        for (int i = 0; i < requestModel.getDates().length - 1; i++) {
            tempDates.append(requestModel.getDates()[i].toString()).append(";");
        }
        tempDates.append(requestModel.getDates()[requestModel.getDates().length - 1].toString());

        out.write(String.valueOf(tempDates));
        out.newLine();

        StringBuilder tempDaysOfWeek = new StringBuilder();
        for (int i = 0; i < requestModel.getDaysOfWeekPostedOn().length - 1; i++) {
            tempDaysOfWeek.append(requestModel.getDaysOfWeekPostedOn()[i].toString()).append(";");
        }
        tempDaysOfWeek.append(requestModel.getDaysOfWeekPostedOn()[requestModel.getDaysOfWeekPostedOn().length - 1].toString());

        out.write(String.valueOf(tempDaysOfWeek));
        out.newLine();
        out.write(requestModel.getDotJots());
        out.close();
    }

}
