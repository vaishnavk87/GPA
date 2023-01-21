## Recordings
### Presentation: https://youtu.be/AnlUHmIfL20
### Calendar GUI: https://youtu.be/ZaYfwACp36E

## Dummy Account ##
There is a dummy account with some simple example courses.
* Username: bob
* Password: password1

# Information for Users 
## Entities:
- Account
    - Each user that uses our program will need to register an account. Each user would have to remember his/her username and password in order to login to his/her account
    - An account contains the username and password, as well as a semester instance that stores all the current courses the user is taking, and an archive which stores all past courses.
    - An account can only be added when a user registers for one. Accounts cannot be removed by users.
    - Users can edit information within their accounts only after a successful login.
- Archive
    - This is where all past courses are stored.
    - The user can archive semester courses and those courses would be removed from the semester and be added to the archive.
- Semester
    - This is where all current courses that the user takes are stored. On the UI, click the green "+" button to add a new course to the semester. The user is required to enter the courseCode, courseName, and credit.
- Course
    -  represent courses that the User is enrolled in. Each course contains a list of all of its assessments, along with other data including
  - The course code (e.g. "Csc207.") Must be unique.
  - The course name (e.g. "Introduction to Software Design")
  - The semester the course is in (e.g. "Winter")
  - How many credits the course is worth (either 0.5 or 1.0)
- CourseEvent
  - A course has title which contains either lectures or tutorials
  - A course has days, start, and end time for lecture or tutorials
  - A course has location for lecture or tutorial
- Assessment
  - Assessments represent a group of marks that are weighed together. This can be a set of 10 quizzes worth 2% each, or a set of term tests where the lowest is dropped, and anything similar.
- Each assessment needs a `WeightScheme` with instructions on how to weigh each mark. Currently, there are 2 types of weight schemes supported
    1. Ordered Weights: For situations where marks are weighed differently based on performance.
    2. Simple Weights: For situations where all marks in the assessment are always worth exactly the same.
- AssessmentInstance 
  - Each assessment has a list of marks called Assessment Instances. Each instance represents a single occurrence of an assessment.
  - Instances are either
    - unmarked (null)
    - marked & not-committed
    - marked & committed
  - A marked & not-committed instance will be used in calculating the hypothetical grade, but not the running grade. It will also not appear in any trends. A committed instance stores the actual mark the user received, and updates the running grade appropriately.
- Calendar
    - An aggregate entity such that ‘StickyNote’ objects can be posted to. The Calendar contains toggle methods for ‘Assessment’, ‘TimeTable’, and ‘Miscellaneous’ sticky notes to make each of them visible or invisible in the Calendar view. The constructor creates a ‘LocalDateTime’ variable called ‘currentDateAndTime’, a ‘LocalDate’ variable called ‘today’, and a ‘LocalDate’ variable called ‘seekedDate’ which equals ‘today’. ‘seekedDate’ can be updated to a different date inputted in the method ‘goToDate’ intended to change the Calendar view to the user’s selected date.
- StickyNote
    - Takes in a ‘String’ called ‘title’, and a ‘String’ called ‘type’. StickyNotes represent events or activities. StickyNotes can be organized into ‘Assessment’, ‘TimeTable’, and ‘Miscellaneous’ sticky notes. The user can write to a sticky note represented by a text file with the method ‘writeToStickyNote’ which takes in an array of ‘Strings’ called ‘dotJots’. The class contains setter and getter methods for the time, the location if applicable, title, and type. The StickyNote can be posted on a single day, multiple specified days, or routinely during the week.
- GPACalculation
    -  Converts percentToGPA (in terms of UofT 4.0 scale), converts percentToLetteredGrade, converts list of percentage to list of GPA, converts percent to list of lettered Grades, compute average, and overall GPA.
- Task
  - convert an assessmentInstance object into a version that suits the presentation in the checklist. Specifically, a task allows the user to choose whether to show the detail of an assessment.
- TaskList
  - Collect all the assessment that the user have in this semester, and convert them into task version.
- TaskCheckList
  - Store all the tasks in three different lists, according to whether they are submitted or committed.
  - checkList
      - checkListTask
          - Convert the AssessmentInstance into another version to be shown in the checklist.
- checkList
  - Contains all the AssessmentInstance of a user.
    - Allow users to sort the assessments in different ways, like due date or weight of the task.
  - checkListView
    - Use java GUI to generate an interface showing the existing checklist. The interface provides functions including choosing ways of sorting, whether to show details of the assessments, and whether to show the submitted and committed assessments.

# Use Cases #
- Account View
  - AddAccountUseCase:
    - Adds a new account to the database. The account should have a username and a password. This use case is triggered by clicking “Register” and entering a username, a password, and a repeat password, and then clicking “register”.
  - LoginAccountUseCase
    - Logins into an existing account. The user will be directed to his/her account’s semester view if he enters the correct credentials and clicks “login”.

- Semester View 
  - AddSemesterCourseUseCase:
    - Adds a new course to the semester. This use case is triggered by the “+” button. Enter a course code, course name, and credit amount (0.5 or 1.0) and click “Add course”.
  - GetAccountTrendUseCase
      - Makes a graph of all the course marks in terms of percentage. To trigger this use case, click on the show trend button. Each time a new course is added a new course will be added on the x-axis and y-axis will display the course mark in terms of percentage.
  - ViewCourseUseCase
      - Shows users courses that have been added in. Displays course name, credit value, assessment title, number of assessment associated with a particular assessment, the weight of the assessment, along with Running Grade being displayed on the bottom with its equivalent letter grade, and hypothetical grade. Use case is triggered by clicking on a course code.
  - ShowCheckList
      - Shows a checklist of assessments from each course from information obtained from course view such as assessment title, and the checklist contains detail of if submitted, committed, uncommitted, and mark. Triggered by clicking checklist button.

- Course View
  - ArchiveCourseUseCase:
    - Moves a semester course to the archive. The archive has no view on the user interface. This is done by clicking the "archive course" button.
  - GetCourseTrend
      - Makes a graph of all the assessments marks in terms of percentage. To trigger this use case click the show course trend button.

- Calendar View
  - GoToSeekedDate
    - Entering a date formatted as yyyy-mm-dd flips the calendar to the respective day, week, or month. Triggered by typing into the text field, and clicking the “Enter” button.
  - GetDayData
    - Returns a LocalDate object of one day. This is formatted as a JPanel. This is used to view a day in the calendar. Triggered by selecting the Day item in the JComboBox.
  - GetWeekData
    - Returns an array of seven LocalDate objects from Sunday to Saturday. Can change with the user-inputted date. This is used to view a week in the calendar. Triggered by selecting the Week item in a JComboBox.
  - GetMonthData
    - Returns an array of up to 42 LocalDate objects. It keeps the month in a six week format, so that the panel size does not change while the month changes. Some cells in the beginning and ending of the view may be blank similar to a real calendar. Triggered by selecting the Month item in a JComboBox.
  - WriteToStickyNote
    - Takes in a title, type (assessment, timetable, or miscellaneous), location (can be an online meeting link), time, date(s), and days of week to be posted on to be passed to a text file. If the user wants to post the sticky note on multiple dates, use “;” to split each date in the respective text field—similarly with the days of week to be posted on. The days of week are meant to post the sticky note on a weekly basis. An exception was not added for multiple sticky notes having the same name, because by design, using the same name overwrites or edits the original sticky note.
  - RemoveStickyNote
    - Deletes the sticky note by file name passed into the title text field.

- NOTE: There are some other use cases, such as RemoveArchiveCourse and RemoveSemesterCourse that are not implemented by the user interface. These use cases are considered as future extensions to the program.

# Test Coverage #
- The AccountTest covers the use cases for account registration and account login.
- The SemesterTest covers the use cases for adding courses to the semester and removing courses from the semester.
- The CourseTest, CourseEventTest, OutlineTest and AssessmentTest implement tests for courses, course events, course outlines and assessments, respectively.
- GPACalculationTest covers tests for the GPACalculator class.
- WeightSchemeTest covers tests for SimpleWeight and OrderedWeight

# DataStorage #
The program is saved to a file called “database.ser”. Changes are saved as the program is used, so there is no need to save manually.

# Known Issues #
- Archive View Button is dead (Archive View exists but the User cannot access it yet)
- Committing a mark without entering a mark quits the entire program
- No way to get from SemesterView to AccountView (no Back button Implementation)
- Cannot remove a mark
- Cannot recommit a mark that was just uncommitted
