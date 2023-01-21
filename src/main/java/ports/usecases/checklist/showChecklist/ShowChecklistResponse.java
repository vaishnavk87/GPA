package ports.usecases.checklist.showChecklist;

import entities.task.TaskChecklist;

public class ShowChecklistResponse {

    public TaskChecklist taskChecklist;

    public ShowChecklistResponse(){taskChecklist = null;}
    public ShowChecklistResponse(TaskChecklist taskChecklist){
        this.taskChecklist = taskChecklist;
    }
}
