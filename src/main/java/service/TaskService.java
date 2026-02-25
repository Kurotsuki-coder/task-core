package service;

import entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositery.TaskRepository;

@Service
public class TaskService {

    @Autowired
    TaskRepository repo;


    public Task createTask(Task task){

        return repo.save(task);

    }

}
