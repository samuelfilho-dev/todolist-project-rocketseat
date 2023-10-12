package br.com.rocketseat.todolist.controllers;

import br.com.rocketseat.todolist.domain.models.TaskModel;
import br.com.rocketseat.todolist.domain.repositories.TaskModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskModelRepository repository;

    @PostMapping("/")
    public ResponseEntity<TaskModel> create(@RequestBody TaskModel task) {
        var dbTask = this.repository.save(task);
        return new ResponseEntity<>(dbTask, HttpStatus.CREATED);
    }
}
