package com.crud.tasks.service;

import com.crud.tasks.Repository.TaskRepository;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Optional<Task> deleteTask(final Long id) {
        return repository.deleteTaskById(id);
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }

    public Task getTaskByIdMyVersion(long id) {
        Task retrievedTask = repository.findTaskById(id);
        System.out.println("Task ID: " + retrievedTask.getId() + "\n" + "Task title: " + retrievedTask.getTitle() + "\n" + "Task content" + retrievedTask.getContent());
        return retrievedTask;
    }

}
