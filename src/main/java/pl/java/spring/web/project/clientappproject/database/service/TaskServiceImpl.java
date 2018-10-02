package pl.java.spring.web.project.clientappproject.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.java.spring.web.project.clientappproject.database.DAO.TaskDAO;
import pl.java.spring.web.project.clientappproject.database.model.Task;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;


    @Override
    @Transactional
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    @Override
    @Transactional
    public void addTask(Task newTask) {
        taskDAO.addTask(newTask);
    }

    @Override
    @Transactional
    public Task getTask(int taskId) {
        return taskDAO.getTask(taskId);
    }

    @Override
    @Transactional
    public void deleteTask(int taskId) {
        taskDAO.deleteTask(taskId);
    }
}
