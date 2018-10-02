package pl.java.spring.web.project.clientappproject.database.DAO;

import pl.java.spring.web.project.clientappproject.database.model.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> getTasks();
    void addTask(Task newTask);
    Task getTask(int taskId);
    void deleteTask(int taskId);
}
