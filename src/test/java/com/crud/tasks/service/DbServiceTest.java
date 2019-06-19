package com.crud.tasks.service;

import com.crud.tasks.Repository.TaskRepository;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.apache.catalina.LifecycleState;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {
    private Logger LOGGER = LoggerFactory.getLogger(DbServiceTest.class);

    @Autowired
    private DbService dbService;

    @Before
    public  void delete () {
        dbService.deleteAllTasks();
    }


    @Test
    public void getAllTasks() {
        //Given
        Task task1 = new Task();
        Task task2 = new Task();

        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        List<Task> taksList = dbService.getAllTasks();

        //Then
        assertEquals(2, taksList.size());

        //cleanUp
        dbService.deleteTask(task1.getId());
        dbService.deleteTask(task2.getId());
    }

    @Test
    public void getTask() {
        //Given
        Task task1 = new Task();

        //When
        dbService.saveTask(task1);
        Long taskId = dbService.getTask(task1.getId()).get().getId();

        //Then
        assertNotNull(dbService.getTask(task1.getId()));
        assertEquals(task1.getId(), taskId);

        //cleanUp
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task();
        //When
        dbService.saveTask(task1);
        //Then
        assertEquals(1, dbService.getAllTasks().size());
        //CleanUp
        dbService.deleteTask(task1.getId());
    }

    @Test
    public void deleteTask() {
        //Given
        Task task1 = new Task();
        Task task2 = new Task();

        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        //Then
        assertEquals(2, dbService.getAllTasks().size());
        dbService.deleteTask(task1.getId());
        assertEquals(1, dbService.getAllTasks().size());

        //CleanUp
        dbService.deleteTask(task2.getId());
    }

}