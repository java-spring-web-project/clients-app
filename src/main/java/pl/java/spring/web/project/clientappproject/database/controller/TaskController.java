package pl.java.spring.web.project.clientappproject.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.java.spring.web.project.clientappproject.database.model.Task;
import pl.java.spring.web.project.clientappproject.database.repository.TaskRepository;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public List<Task> retrieveAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task retrieveTask(@PathVariable long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (!task.isPresent()) {
            throw new EntityNotFoundException();//TODO: find more suitable exception
        }

        return task.get();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addTask(@RequestBody Task task) {

        Task savedTask = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{$id}")
                .buildAndExpand(savedTask.getTaskId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (!taskOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        task.setTaskId(id);

        taskRepository.save(task);

        return ResponseEntity.noContent().build();
    }
}
