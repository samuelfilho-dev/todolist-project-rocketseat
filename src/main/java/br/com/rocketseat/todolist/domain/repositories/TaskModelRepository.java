package br.com.rocketseat.todolist.domain.repositories;

import br.com.rocketseat.todolist.domain.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskModelRepository extends JpaRepository<TaskModel, UUID> {
}
