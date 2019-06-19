package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.taskfacade.TaskFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedQuery;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private TaskFacade taskFacade;

    @GetMapping(value = "tasks")
    public List<TaskDto> getTasks() {
        return taskFacade.fetchTaskList();
    }

    @GetMapping(value = "tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskFacade.fetchTask(taskId);
    }

    @DeleteMapping(value = "tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        taskFacade.deleteTask(taskId);
    }

    @PutMapping(value = "tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskFacade.updateTask(taskDto);
    }

    @PostMapping(value = "tasks", consumes = APPLICATION_JSON_VALUE)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskFacade.createTask(taskDto);
    }
}
