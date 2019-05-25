package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "title1", "content1");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto1);
        //Then
        assertEquals(1L, mappedTask.getId(), 0);
        assertEquals("title1", mappedTask.getTitle());
        assertEquals("content1", mappedTask.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task1 = new Task(1L, "title1", "content1");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals(1L, mappedTaskDto.getId(), 0);
        assertEquals("title1", mappedTaskDto.getTitle());
        assertEquals("content1", mappedTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "title1", "content1");
        Task task2 = new Task(2L, "title2", "content2");
        Task task3 = new Task(3L, "title3", "content3");
        Task task4 = new Task(4L, "title4", "content4");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(4, taskDtos.size());
    }
}