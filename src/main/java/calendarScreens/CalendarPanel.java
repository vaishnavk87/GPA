package calendarScreens;

import entities.calendarSystem.CalendarFactory;
import entities.calendarSystem.CommonStickyNoteFactory;
import entities.calendarSystem.StickyNoteCalendarFactory;
import entities.calendarSystem.StickyNoteFactory;
import ports.usecases.calendar.goToSeekedDateUseCase.GoToSeekedDateInteractor;
import ports.usecases.calendar.goToSeekedDateUseCase.GoToSeekedDateOutputBoundary;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteInteractor;
import ports.usecases.calendar.removeStickyNoteUseCase.RemoveStickyNoteOutputBoundary;
import ports.usecases.calendar.week.calendarModelWeekRequest;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteDsGateway;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteInteractor;
import ports.usecases.calendar.writeToStickyNoteUseCase.WriteToStickyNoteOutputBoundary;
import usecases.calendar.calendarModelWeekUseCaseInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The calendar panel displays a calendar (one day, one week, or one month), and the sticky note editor. The sticky
 * note editor allows the user to write and remove sticky notes. Sticky notes are saved as text files for persistence,
 * and can be posted on their assigned dates. The sticky notes can be clicked on to be enlarged.
 * The user can also look at another date by typing in that date. The user can also flip through the pages
 * of the calendar.
 * */
public class CalendarPanel extends JPanel implements ActionListener {
    GoToSeekedDateController goToSeekedDateController;
    calendarModelDayController calendarModelDayController;
    calendarModelMonthController calendarModelMonthController;
    calendarModelWeekController calendarModelWeekController;
    JButton goToSeekedDateButton;
    JTextField goToSeekedDateTextfield;
    JLabel goToSeekedDateLabel, viewDayOrWeekOrMonthLabel;
    JComboBox<String> viewDayOrWeekOrMonth;
    JToggleButton toggleAssessmentButton, toggleTimetableButton, toggleMiscellaneousButton;
    WriteToStickyNoteOutputBoundary outputBoundary;
    StickyNoteFactory stickyNoteFactory;
    WriteToStickyNoteDsGateway stickyNoteGateway;
    WriteToStickyNoteInteractor interactor;
    WriteToStickyNoteController writeToStickyNoteController;
    RemoveStickyNoteOutputBoundary removalOutputBoundary;
    RemoveStickyNoteDataAccess removalGateway;
    RemoveStickyNoteInteractor removalInteractor;
    RemoveStickyNoteController removeStickyNoteController;
    StickyNoteEditorScreen stickyNoteEditorScreen;
    GridBagConstraints gbc;
    CardLayout cardLayout;
    CalendarFactory calendar;
    DayScreen dayScreen;
    WeekScreen weekScreen;
    MonthScreen monthScreen;
    GoToSeekedDateOutputBoundary goToSeekedDateOutputBoundary;
    GoToSeekedDateInteractor goToSeekedDateInteractor;

    public CalendarPanel() throws IOException {
        goToSeekedDateButton = new JButton("Enter");
        goToSeekedDateTextfield = new JTextField(10);
        goToSeekedDateLabel = new JLabel("Go to: ");
        //JButton nextPage = new BasicArrowButton(BasicArrowButton.EAST);
        //JButton prevPage = new BasicArrowButton(BasicArrowButton.WEST);
        viewDayOrWeekOrMonthLabel = new JLabel("View: ");
        viewDayOrWeekOrMonth = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
        toggleAssessmentButton = new JCheckBox("Assessments");
        toggleTimetableButton = new JCheckBox("Timetable");
        toggleMiscellaneousButton = new JCheckBox("Miscellaneous");

        outputBoundary = new WriteToStickyNotePresenter();
        stickyNoteFactory = new CommonStickyNoteFactory();
        stickyNoteGateway = new WriteToStickyNoteDataAccess();
        interactor = new WriteToStickyNoteInteractor(stickyNoteGateway, stickyNoteFactory, outputBoundary);
        writeToStickyNoteController = new WriteToStickyNoteController(interactor);

        removalOutputBoundary = new RemoveStickyNotePresenter();
        removalGateway = new RemoveStickyNoteDataAccess();
        removalInteractor = new RemoveStickyNoteInteractor(removalGateway, removalOutputBoundary);
        removeStickyNoteController = new RemoveStickyNoteController(removalInteractor);
        stickyNoteEditorScreen = new StickyNoteEditorScreen(writeToStickyNoteController, removeStickyNoteController);

        goToSeekedDateOutputBoundary = new GoToSeekedDatePresenter();
        goToSeekedDateInteractor = new GoToSeekedDateInteractor(goToSeekedDateOutputBoundary);
        goToSeekedDateController = new GoToSeekedDateController(goToSeekedDateInteractor);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        cardLayout = new CardLayout();

        calendar = new StickyNoteCalendarFactory();
        DayCell cell;
        //Start-up:
        //create starting calendar with getMonthData use case (or getWeekData or getDayData)
        //load sticky notes associated with the account.
        //The account doesn't have to have its own calendar, because a calendar is constant, e.g. Dec 1st, 2022 is always
        //a Thursday. It can't become a Monday. The account does at least need to save the sticky notes (text files)
        //somewhere to be able to retrieve them every time the app is reopened, e.g. a folder named the account's username.
        //The nextPage and prevPage buttons can use the GoToSeekedDate use case, and
        //LocalDate.minus/plus(1 day, 7 days, or 1 month) to move by 1 day, 1 week, or 1 month depending on which view
        //the calendar is currently set to. Note that the minus and plus methods work with both negative and positive
        //values, so it is possible to only use one of them for both nextPage and prevPage.

        cell = new DayCell(calendar.create().getSeekedDate());
        dayScreen = new DayScreen(cell);
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.gridwidth = 5;
        //gbc.gridheight = 6;
        //dayScreen.remove(cell);
        this.add(dayScreen, gbc);

        gbc.gridx = 1/*+5*/;
        gbc.gridy = 0;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        this.add(viewDayOrWeekOrMonthLabel, gbc);

        gbc.gridx = 2/*+5*/;
        this.add(viewDayOrWeekOrMonth, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1/*+5*/;
        this.add(goToSeekedDateLabel, gbc);

        gbc.gridx = 2/*+5*/;
        this.add(goToSeekedDateTextfield, gbc);

        gbc.gridx = 3/*+5*/;
        this.add(goToSeekedDateButton, gbc);

        gbc.gridx = 1/*+5*/;
        gbc.gridy = 2;
        this.add(toggleAssessmentButton, gbc);
        gbc.gridy = 3;
        this.add(toggleMiscellaneousButton, gbc);
        gbc.gridy = 4;
        this.add(toggleTimetableButton, gbc);

        gbc.gridy = 5;
        this.add(stickyNoteEditorScreen);
    }
        @Override
        public void actionPerformed (ActionEvent event){
            String eventName = event.getActionCommand();

            if (eventName.equals("Enter")) {
                //GoToSeekedDate use case
                goToSeekedDateOutputBoundary = new GoToSeekedDatePresenter();
                goToSeekedDateInteractor = new GoToSeekedDateInteractor(goToSeekedDateOutputBoundary);
                goToSeekedDateController = new GoToSeekedDateController(goToSeekedDateInteractor);
                gbc.gridx = 0;
                gbc.gridy = 0;
                this.remove(dayScreen);
                try {
                    dayScreen = new DayScreen(new DayCell(LocalDate.parse(goToSeekedDateTextfield.getText())));
                    this.add(dayScreen, gbc);
                    this.revalidate();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if (eventName.equals("Daily")) {
                //GetDayData use case (and card layout switch?)
                try {
                    dayScreen = new DayScreen(new DayCell(calendar.create().getSeekedDate()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (eventName.equals("Weekly")) {
                //GetWeekData use case
                calendarModelWeekPresenter calendarModelDayPresenter = new calendarModelWeekPresenter();
                calendarModelWeekUseCaseInteractor week = new calendarModelWeekUseCaseInteractor(calendarModelDayPresenter);
                calendarModelWeekRequest requestModel = new calendarModelWeekRequest(LocalDate.parse(goToSeekedDateTextfield.getText()));
                //calendarModelWeekController = new calendarModelWeekController(week.execute(requestModel));
                DayCell[] sevenDays = new DayCell[7];
//                for (int i = 0; i < sevenDays.length; i++) {
//                    sevenDays[i] = new DayCell();
//                }
                weekScreen = new WeekScreen(sevenDays);
            }
            if (eventName.equals("Monthly")) {
                //GetMonthData use case
            }
        }
}
