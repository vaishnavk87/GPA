package entities.task;

import entities.assessment.AssessmentInstance;

import java.time.LocalDateTime;

public abstract class Task {
    public abstract boolean getSubmitted();

    public abstract LocalDateTime getDueTime();

    public abstract double getMark();

    public abstract boolean getCommitted();

    public abstract boolean isHaveMark();

    public abstract boolean isShowDetail();

    public abstract void toShowDetail();

    public abstract void toNotShowDetail();

}
