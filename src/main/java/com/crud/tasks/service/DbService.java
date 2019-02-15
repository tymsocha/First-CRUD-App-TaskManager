package com.crud.tasks.service;

import com.crud.tasks.Repository.TaskRepository;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Statement;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public Task getTaskByIdMyVersion(long id) {
        Task retrievedTask = repository.getTaskByItsId(id);
        System.out.println("Task ID: " + retrievedTask.getId() + "\n" + "Task title: " + retrievedTask.getTitle() + "\n" + "Task content" + retrievedTask.getContent());
        return retrievedTask;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

}
