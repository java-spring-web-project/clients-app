package pl.java.spring.web.project.clientappproject.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.web.project.clientappproject.database.model.Task;
import pl.java.spring.web.project.clientappproject.database.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/list")
    public String list(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);

        return "list-tasks";
    }

    @GetMapping("/new-task")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @GetMapping("/add-task")
    public String addTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task);

        return "redirect:/task/list-task";
    }

    @GetMapping("/view-task")
    public String viewTask(@RequestParam("taskId") int taskId, Model model) {
        Task task = taskService.getTask(taskId);

        return "task-form";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") int taskId, Model model) {
        taskService.deleteTask(taskId);

        return "redirect:/task/list-task";
    }
}
