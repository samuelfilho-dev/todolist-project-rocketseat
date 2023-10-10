package br.com.rocketseat.todolist.controllers;

import br.com.rocketseat.todolist.domain.models.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {

        System.out.println(user.getName());

        return ResponseEntity.ok().build();
    }
}
