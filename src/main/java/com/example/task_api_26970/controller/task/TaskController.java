package com.example.task_api_26970.controller.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_api_26970.model.task.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();

    public TaskController() {
        taskList.add(new Task(1L, "Study Spring", "Read REST concepts", false, "HIGH", "2026-02-20"));
        taskList.add(new Task(2L, "Buy groceries", "Milk and bread", true, "LOW", "2026-02-18"));
        taskList.add(new Task(3L, "Finish assignment", "Complete question 5", false, "MEDIUM", "2026-02-25"));
    }

    // GET all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskList;
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        for (Task t : taskList) {
            if (t.getTaskId().equals(taskId)) {
                return t;
            }
        }
        throw new RuntimeException("Task not found");
    }

    // GET by completion status
    @GetMapping("/status")
    public List<Task> getByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : taskList) {
            if (t.isCompleted() == completed) {
                result.add(t);
            }
        }
        return result;
    }

    // GET by priority
    @GetMapping("/priority/{priority}")
    public List<Task> getByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                result.add(t);
            }
        }
        return result;
    }

    // POST create new task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        taskList.add(task);
        return task;
    }

    // PUT update task
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {

        for (Task t : taskList) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(updatedTask.getTitle());
                t.setDescription(updatedTask.getDescription());
                t.setCompleted(updatedTask.isCompleted());
                t.setPriority(updatedTask.getPriority());
                t.setDueDate(updatedTask.getDueDate());
                return t;
            }
        }

        throw new RuntimeException("Task not found");
    }

    // PATCH mark as completed
    @PatchMapping("/{taskId}/complete")
    public Task markCompleted(@PathVariable Long taskId) {

        for (Task t : taskList) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return t;
            }
        }

        throw new RuntimeException("Task not found");
    }

    // DELETE task
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        taskList.removeIf(t -> t.getTaskId().equals(taskId));
    }
}
