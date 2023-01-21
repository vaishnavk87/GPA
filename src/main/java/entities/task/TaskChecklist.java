package entities.task;

import java.util.List;

public abstract class TaskChecklist {
    public abstract void sortInDDL();

    public abstract void sortInMark();

    public abstract void toShowDetail();

    public abstract void toNotShowDetail();

    public abstract void toShowToDo();

    public abstract void toNotShowToDo();

    public abstract void toShowCommitted();

    public abstract void toNotShowCommitted();

    public abstract void toShowSubmitted();

    public abstract void toNotShowSubmitted();

    public abstract List<Task> getTaskToDo();

    public abstract List<Task> getTaskCommitted();

    public abstract List<Task> getTaskSubmitted();
}
