package inMemoryDB.entities;

import entities.assessment.AssessmentInstance;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AssessmentInstanceImpl extends AssessmentInstance implements Serializable {
    private String title;
    private LocalDateTime deadline;
    private boolean isCommitted = false;
    private boolean isSubmitted = false;
    private Double mark = null;

    public AssessmentInstanceImpl(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean isCommitted() {
        return isCommitted;
    }

    @Override
    public void setCommitted(boolean committed) {
        isCommitted = committed;
    }

    @Override
    public boolean isSubmitted() {
        return isSubmitted;
    }

    @Override
    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    @Override
    public Double getMark() {
        return mark;
    }

    @Override
    public void setMark(Double mark) {
        this.mark = mark;
    }
}
