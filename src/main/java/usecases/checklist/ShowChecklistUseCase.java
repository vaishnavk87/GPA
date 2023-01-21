package usecases.checklist;

import entities.account.Account;
import entities.task.TaskChecklist;
import entities.task.TaskList;
import inMemoryDB.entities.TaskChecklistImpl;
import inMemoryDB.entities.TaskListImpl;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.checklist.showChecklist.ShowChecklistInputBoundary;
import ports.usecases.checklist.showChecklist.ShowChecklistRequest;
import ports.usecases.checklist.showChecklist.ShowChecklistResponse;

public class ShowChecklistUseCase implements ShowChecklistInputBoundary {
    private final EntityGateway entityGateway;

    public ShowChecklistUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    @Override
    public ShowChecklistResponse execute(ShowChecklistRequest request) throws PathNotFoundError {
        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError("username: " + request.username);
        }
        Account account = entityGateway.loadAccount(request.username);
        TaskList taskList = new TaskListImpl(account);
        if (taskList.getTaskList().size() == 0){
            return new ShowChecklistResponse();
        }
        return showChecklist(taskList, request);
    }

    public ShowChecklistResponse showChecklist(TaskList taskList, ShowChecklistRequest request) {
        TaskChecklist taskChecklist = new TaskChecklistImpl(taskList);
        if (request.showTodo) {
            taskChecklist.toShowToDo();
        } else {
            taskChecklist.toNotShowToDo();
        }
        if (request.showCommitted) {
            taskChecklist.toShowCommitted();
        } else {
            taskChecklist.toNotShowCommitted();
        }
        if (request.showSubmitted) {
            taskChecklist.toShowSubmitted();
        } else {
            taskChecklist.toNotShowSubmitted();
        }
        if (request.showDetail) {
            taskChecklist.toShowDetail();
        } else {
            taskChecklist.toNotShowDetail();
        }
        if (request.sortInDDL) {
            taskChecklist.sortInDDL();
        }
        if (request.sortInMark) {
            taskChecklist.sortInMark();
        }
        return new ShowChecklistResponse(taskChecklist);
    }

}

