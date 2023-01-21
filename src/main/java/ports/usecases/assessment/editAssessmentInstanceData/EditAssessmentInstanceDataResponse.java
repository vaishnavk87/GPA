package ports.usecases.assessment.editAssessmentInstanceData;

import java.time.LocalDateTime;

public class EditAssessmentInstanceDataResponse {
    public String newTitle;
    public LocalDateTime newDeadline;

    public EditAssessmentInstanceDataResponse() {
    }

    public EditAssessmentInstanceDataResponse(String newTitle, LocalDateTime newDeadline) {
        this.newTitle = newTitle;
        this.newDeadline = newDeadline;
    }
}
