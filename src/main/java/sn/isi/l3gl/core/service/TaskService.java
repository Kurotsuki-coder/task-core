```
package sn.isi.l3gl.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.entity.Status;
import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    // GET /api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    // PUT /api/tasks/{id}/status
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        return ResponseEntity.ok(taskService.updateStatus(id, Status.valueOf(status)));
    }

    // GET /api/tasks/done/count
    @GetMapping("/done/count")
    public ResponseEntity<Long> countCompletedTasks() {
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
} et voila celui le taskservice package sn.isi.l3gl.core.service;

import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.entity.Status;
import sn.isi.l3gl.core.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Injection via constructeur
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setStatus(Status.TODO);
        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public Task updateStatus(Long taskId, Status newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public long countCompletedTasks() {
        return taskRepository.countByStatus(Status.DONE);
    }
}
```
