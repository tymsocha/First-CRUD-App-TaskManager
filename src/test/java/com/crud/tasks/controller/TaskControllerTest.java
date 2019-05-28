package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.taskfacade.TaskFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskFacade taskFacade;

    @Test
    public void getTasks() throws Exception {
        //Given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L, "Test1", "Test desc 1"));
        taskList.add(new TaskDto(2L, "Test2", "Test desc 2"));

        when(taskFacade.fetchTaskList()).thenReturn(taskList);

        //When $ Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Test1")))
                .andExpect(jsonPath("$[0].content", Matchers.is("Test desc 1")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", Matchers.is("Test2")))
                .andExpect(jsonPath("$[1].content", Matchers.is("Test desc 2")));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test1", "Test desc 1");

        when(taskFacade.fetchTask(org.mockito.Matchers.any())).thenReturn(taskDto);

        //When $ Then
        mockMvc.perform(get("/v1/task/getTask?taskId=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Test1")))
                .andExpect(jsonPath("$.content", Matchers.is("Test desc 1")));

    }

    @Test
    public void deleteTask() throws Exception {
        //Given, When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test1", "Test desc 1");

        when(taskFacade.updateTask(org.mockito.Matchers.any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Test1")))
                .andExpect(jsonPath("$.content", Matchers.is("Test desc 1")));
    }

    @Test
    public void createTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test1", "Test desc 1");

        when(taskFacade.createTask(org.mockito.Matchers.any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Test1")))
                .andExpect(jsonPath("$.content", Matchers.is("Test desc 1")));
    }
}