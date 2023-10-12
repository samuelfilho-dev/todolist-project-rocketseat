package br.com.rocketseat.todolist.controllers;

import br.com.rocketseat.todolist.domain.models.TaskModel;
import br.com.rocketseat.todolist.domain.repositories.TaskModelRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskModelRepository repository;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody TaskModel task, HttpServletRequest request) {

        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            return new ResponseEntity<>("StartAt and EndAt must be greater than Current Date", HttpStatus.BAD_REQUEST);
        }

        if (task.getStartAt().isAfter(task.getEndAt())){
            return new ResponseEntity<>("StartAt must be smaller than EndAt", HttpStatus.BAD_REQUEST);
        }

        var dbTask = this.repository.save(task);
        return new ResponseEntity<>(dbTask, HttpStatus.CREATED);
    }
}
