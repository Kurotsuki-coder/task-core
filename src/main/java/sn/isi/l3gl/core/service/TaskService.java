package sn.isi.l3gl.core.service;

import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.entity.Status;
import sn.isi.l3gl.core.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    // VERSION 0.0.1
    public Task createTask(Task task){

        task.setStatus(Status.TODO);

        return repo.save(task);

    }

    // VERSION 0.1.0
    public List<Task> listTasks(){

        return repo.findAll();

    }

}
