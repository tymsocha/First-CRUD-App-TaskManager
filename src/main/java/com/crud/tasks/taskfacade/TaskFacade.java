package com.crud.tasks.taskfacade;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskFacade {
    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDto> fetchTaskList() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    public TaskDto fetchTask(final Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    public void deleteTask(final Long taskId) throws TaskNotFoundException {
        service.deleteTask(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public TaskDto updateTask(final TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    public TaskDto createTask(final TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
        return taskDto;
    }
}
