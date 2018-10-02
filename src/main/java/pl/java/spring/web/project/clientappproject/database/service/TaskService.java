package pl.java.spring.web.project.clientappproject.database.service;

import pl.java.spring.web.project.clientappproject.database.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    void addTask(Task newTask);
    Task getTask(int taskId);
    void deleteTask(int taskId);
}
