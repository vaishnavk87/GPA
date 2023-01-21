package entities.assessment;

import java.time.LocalDateTime;

public abstract class AssessmentInstance {
    public abstract String getTitle();

    public abstract LocalDateTime getDeadline();

    public abstract Double getMark();

    public abstract boolean isCommitted();

    public abstract boolean isSubmitted();

    public abstract void setTitle(String title);

    public abstract void setDeadline(LocalDateTime deadline);

    public abstract void setMark(Double mark);

    public abstract void setCommitted(boolean isCommitted);

    public abstract void setSubmitted(boolean isSubmitted);

    public interface AssessmentInstanceFactory {
        AssessmentInstance createAssessmentInstance(String title, LocalDateTime deadline, Double mark,
                                                    boolean isCommitted, boolean isSubmitted);
    }
}
