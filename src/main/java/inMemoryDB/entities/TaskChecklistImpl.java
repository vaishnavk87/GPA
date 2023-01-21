package inMemoryDB.entities;

import entities.task.Task;
import entities.task.TaskChecklist;
import entities.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskChecklistImpl extends TaskChecklist {
    private List<Task> taskToDo = new ArrayList<>();
    private List<Task> taskCommitted = new ArrayList<>();
    private List<Task> taskSubmitted = new ArrayList<>();
    private Boolean showDetail = false;
    private Boolean showToDo = true;
    private Boolean showCommitted = false;
    private Boolean showSubmitted = false;
    private Boolean haveTask = true;

    public TaskChecklistImpl(TaskList taskList){
        if (taskList == null){
            haveTask = false;
        }
        for (Task task: taskList.getTaskList()) {
            if (task.getCommitted()) {
                this.taskCommitted.add(task);
            }
            if (task.getSubmitted()) {
                this.taskSubmitted.add(task);
            }
            if (!task.getSubmitted() && !task.getCommitted()) {
                this.taskToDo.add(task);
            }
        }
    }

    @Override
    public void sortInDDL(){
        List<Task> newTaskListT = taskToDo;
        for (int i = 0; i < newTaskListT.size(); i++){
            for (int j = i + 1; j < newTaskListT.size(); j++){
                if (newTaskListT.get(j).getDueTime().isBefore(newTaskListT.get(i).getDueTime())){
                    Task temp;
                    temp = newTaskListT.get(j);
                    newTaskListT.set(j, newTaskListT.get(i));
                    newTaskListT.set(i, temp);
                }
            }
        }
        taskToDo = newTaskListT;
        List<Task> newTaskListS = taskSubmitted;
        for (int i = 0; i < newTaskListS.size(); i++){
            for (int j = i + 1; j < newTaskListS.size(); j++){
                if (newTaskListS.get(j).getDueTime().isBefore(newTaskListS.get(i).getDueTime())){
                    Task temp;
                    temp = newTaskListS.get(j);
                    newTaskListS.set(j, newTaskListS.get(i));
                    newTaskListS.set(i, temp);
                }
            }
        }
        taskSubmitted = newTaskListS;
        List<Task> newTaskListC = taskToDo;
        for (int i = 0; i < newTaskListC.size(); i++){
            for (int j = i + 1; j < newTaskListC.size(); j++){
                if (newTaskListC.get(j).getDueTime().isBefore(newTaskListC.get(i).getDueTime())){
                    Task temp;
                    temp = newTaskListC.get(j);
                    newTaskListC.set(j, newTaskListC.get(i));
                    newTaskListC.set(i, temp);
                }
            }
        }
        taskCommitted = newTaskListC;
    }

    @Override
    public void sortInMark(){
        List<Task> newTaskListT = taskToDo;
        for (int i = 0; i < newTaskListT.size(); i++){
            for (int j = i + 1; j < newTaskListT.size(); j++){
                if (newTaskListT.get(j).getMark() < newTaskListT.get(i).getMark()){
                    Task temp;
                    temp = newTaskListT.get(j);
                    newTaskListT.set(j, newTaskListT.get(i));
                    newTaskListT.set(i, temp);
                }
            }
        }
        taskToDo = newTaskListT;
        List<Task> newTaskListS = taskSubmitted;
        for (int i = 0; i < newTaskListS.size(); i++){
            for (int j = i + 1; j < newTaskListS.size(); j++){
                if (newTaskListS.get(j).getMark() < newTaskListS.get(i).getMark()){
                    Task temp;
                    temp = newTaskListS.get(j);
                    newTaskListS.set(j, newTaskListS.get(i));
                    newTaskListS.set(i, temp);
                }
            }
        }
        taskSubmitted = newTaskListS;
        List<Task> newTaskListC = taskToDo;
        for (int i = 0; i < newTaskListC.size(); i++){
            for (int j = i + 1; j < newTaskListC.size(); j++){
                if (newTaskListC.get(j).getMark() < newTaskListC.get(i).getMark()){
                    Task temp;
                    temp = newTaskListC.get(j);
                    newTaskListC.set(j, newTaskListC.get(i));
                    newTaskListC.set(i, temp);
                }
            }
        }
        taskCommitted = newTaskListC;
    }

    @Override
    public void toShowDetail(){
        this.showDetail = true;
        for (Task i: taskToDo){
            i.toShowDetail();
        }
        for (Task j: taskSubmitted){
            j.toShowDetail();
        }
        for (Task k: taskCommitted){
            k.toShowDetail();
        }
    }

    @Override
    public void toNotShowDetail(){
        this.showDetail = true;
        for (Task i: taskToDo){
            i.toNotShowDetail();
        }
        for (Task j: taskSubmitted){
            j.toNotShowDetail();
        }
        for (Task k: taskCommitted){
            k.toNotShowDetail();
        }
    }

    @Override
    public void toShowToDo(){
        this.showToDo = true;
    }

    @Override
    public void toNotShowToDo(){
        this.showToDo = false;
    }

    @Override
    public void toShowCommitted(){
        this.showCommitted = true;
    }

    @Override
    public void toNotShowCommitted(){
        this.showCommitted = false;
    }

    @Override
    public void toShowSubmitted(){
        this.showSubmitted = true;
    }

    @Override
    public void toNotShowSubmitted(){
        this.showSubmitted = false;
    }


    public String toString(){
        if (!haveTask){
            return "";
        }
        String taskT = "Tasks to do: \n \n";
        for (Task i: this.taskToDo){
            taskT += i.toString() + "\n \n";
        }
        String taskC = "Tasks committed: \n \n";
        for (Task i: this.taskCommitted){
            taskC += i.toString() + "\n \n";
        }
        String taskS = "Tasks submitted: \n \n";
        for (Task i: this.taskSubmitted){
            taskS += i.toString() + "\n \n";
        }
        String result = "";
        if (this.showCommitted){
            result += taskC;
        }
        if (this.showSubmitted){
            result += taskS;
        }
        if (this.showToDo){
            result += taskT;
        }
        return result;
    }

    @Override
    public List<Task> getTaskToDo() {
        return taskToDo;
    }


    @Override
    public List<Task> getTaskCommitted() {
        return taskCommitted;
    }

    @Override
    public List<Task> getTaskSubmitted() {
        return taskSubmitted;
    }
}
