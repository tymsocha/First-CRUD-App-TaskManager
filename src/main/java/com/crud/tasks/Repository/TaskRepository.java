package com.crud.tasks.Repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    Optional<Task> findById(Long id);

    Optional<Task> deleteTaskById(Long id);

    @Query
    Task findTaskById(@Param("ID") long id);
}
