package ports.usecases.checklist.showChecklist;

import entities.account.Account;
import entities.task.TaskChecklist;
import inMemoryDB.entities.TaskChecklistImpl;
import inMemoryDB.entities.TaskListImpl;

public class ShowChecklistRequest {
    public String username;
    public Boolean showTodo;
    public Boolean showCommitted;
    public Boolean showSubmitted;
    public Boolean showDetail;
    public Boolean sortInDDL;
    public Boolean sortInMark;

    public ShowChecklistRequest(String username, Boolean showTodo, Boolean showCommitted,
                                Boolean showSubmitted, Boolean showDetail, Boolean sortInDDL, Boolean sortInMark) {
        this.username = username;
        this.showCommitted = showCommitted;
        this.showDetail = showDetail;
        this.showSubmitted = showSubmitted;
        this.showTodo = showTodo;
        this.sortInDDL = sortInDDL;
        this.sortInMark = sortInMark;
    }
}
