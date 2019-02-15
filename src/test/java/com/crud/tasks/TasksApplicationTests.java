package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLombok() {
		//Given
		TaskDto taskDto = new TaskDto((long) 1, "Test Lombok", "I want to break free");
		//When
		long id = taskDto.getId();
		String title = taskDto.getTitle();
		String content = taskDto.getContent();
		//Then
		Assert.assertEquals(1, id);
		Assert.assertEquals("Test Lombok", title);
		Assert.assertEquals("I want to break free", content);
	}

	@Test
	public void testDbService() {
		//Given
		DbService dbService = new DbService();
		//When
		Task testTask = dbService.getTaskByIdMyVersion((long) 1);
		String title = "test";
		String content = "test1";
		//Then
		Assert.assertEquals(title, testTask.getTitle());
		Assert.assertEquals(content, testTask.getContent());
	}

}

