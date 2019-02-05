package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
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

}

